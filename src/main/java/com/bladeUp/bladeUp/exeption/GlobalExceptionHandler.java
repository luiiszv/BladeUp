package com.bladeUp.bladeUp.exeption;

import com.bladeUp.bladeUp.payload.ApiResponse;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.boot.json.JsonParseException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE) // ¡Esta línea es crucial!

public class GlobalExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<?>> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        // 1. Mensaje por defecto
        String message = "Solicitud JSON inválida";
        Map<String, Object> details = new HashMap<>();

        // 2. Analizar la causa raíz
        Throwable rootCause = ex.getMostSpecificCause();

        if (rootCause instanceof JsonParseException) {
            message = "JSON malformado: estructura incorrecta";
            details.put("error", rootCause.getMessage());
        } else if (rootCause instanceof InvalidFormatException) {
            InvalidFormatException ife = (InvalidFormatException) rootCause;
            message = "Tipo de dato incorrecto";

            // Manejo seguro de campos
            String fieldName = ife.getPath().isEmpty() ? "desconocido"
                    : ife.getPath().get(ife.getPath().size() - 1).getFieldName();

            details.put("campo", fieldName);
            details.put("valorRecibido", ife.getValue());
            details.put("tipoEsperado", ife.getTargetType().getSimpleName());
        } else if (rootCause instanceof JsonMappingException) {
            message = "Mapeo JSON inválido";
            details.put("error", rootCause.getMessage());
        } else {
            details.put("error", rootCause.getMessage());
        }

        // 3. Construir respuesta
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(message, details));
    }


    // 2. Manejo de excepciones específicas de autenticación
    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<ApiResponse<?>> handleDisabledException(DisabledException ex) {
        Map<String, String> details = new HashMap<>();
        details.put("reason", "account_disabled");
        details.put("solution", "Contacte al administrador");

        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(ApiResponse.error(ex.getMessage(), details));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiResponse<?>> handleAuthenticationException(AuthenticationException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ApiResponse.error(ex.getMessage()));
    }

    // 3. Manejo de excepciones comunes de Spring/Java
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleEntityNotFound(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error(ex.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<?>> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(ex.getMessage()));
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ApiResponse<?>> handleResponseStatusException(ResponseStatusException ex) {
        return ResponseEntity.status(ex.getStatusCode())
                .body(ApiResponse.error(ex.getReason()));
    }



    //Vlida que los campos correctos se envien en el req.body sisi
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error("Error de validación", errors));
    }
}
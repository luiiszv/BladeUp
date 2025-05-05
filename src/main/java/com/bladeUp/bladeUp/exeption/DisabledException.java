package com.bladeUp.bladeUp.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Excepción para cuentas desactivadas
 * Devuelve automáticamente HTTP 403 (Forbidden)
 */
@ResponseStatus(HttpStatus.FORBIDDEN)
public class DisabledException extends AuthenticationException {
    public DisabledException(String message) {
        super(message);
    }
}
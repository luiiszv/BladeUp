package com.bladeUp.bladeUp.controller;


import com.bladeUp.bladeUp.model.User;
import com.bladeUp.bladeUp.model.dto.request.LoginRequestDto;
import com.bladeUp.bladeUp.model.dto.response.AuthResponse;
import com.bladeUp.bladeUp.payload.ApiResponse;
import com.bladeUp.bladeUp.service.JwtService;
import com.bladeUp.bladeUp.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.oauth2.jwt.Jwt;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")

public class AuthController {

    private UserService userService;


    private final JwtService jwtService;


    @Autowired
    public AuthController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    private static final Logger log = LoggerFactory.getLogger(UserService.class);


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @Valid @RequestBody LoginRequestDto req) {

        // Log para seguimiento (nivel DEBUG)
        log.debug("Intento de login para email: {}", req.email());


        User user = userService.login(req.email(), req.password());

        // Log exitoso (nivel INFO)
        log.info("Login exitoso para userId: {}", user.getUserId());


        // Generar el JWT
        String token = jwtService.generateToken(user);


        return ResponseEntity.ok(
                new AuthResponse(
                        user.getUserId(),
                        user.getEmail(),
                        user.getFirstName() + " " + user.getLastName(),
                        user.isActive(),
                        token
                )
        );

    }

    @GetMapping("/verify")
    public ResponseEntity<?> verifyToken(@AuthenticationPrincipal Jwt jwt) {
        if (jwt == null) {
            return ResponseEntity.badRequest().body(
                    ApiResponse.error("Token inválido")
            );
        }
        Map<String, Object> claims = jwt.getClaims();
        return ResponseEntity.ok(
                ApiResponse.success("Token válido", claims)
        );
    }
}

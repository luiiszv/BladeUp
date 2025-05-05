package com.bladeUp.bladeUp.controller;


import com.bladeUp.bladeUp.model.User;
import com.bladeUp.bladeUp.model.dto.request.LoginRequestDto;
import com.bladeUp.bladeUp.model.dto.response.AuthResponse;
import com.bladeUp.bladeUp.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")


public class AuthController {

    private UserService userService;


    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
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

        return ResponseEntity.ok(
                new AuthResponse(
                        user.getUserId(),
                        user.getEmail(),
                        user.getFirstName() + " " + user.getLastName(),
                        user.isActive()
                )
        );

    }


}

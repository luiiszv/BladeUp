package com.bladeUp.bladeUp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityFilterConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Desactiva protección CSRF (solo para desarrollo)
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // Permite acceso sin autenticación a todas las rutas
                );

        return http.build();
    }
}
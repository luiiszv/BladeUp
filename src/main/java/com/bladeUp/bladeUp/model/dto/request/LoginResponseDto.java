package com.bladeUp.bladeUp.model.dto.request;


import lombok.Builder;

@Builder
public record LoginResponseDto(
        boolean success,
        String message,
        Long userId,
        String email,
        String fullName,
        String role,
        String token // Si usas JWT
) {}

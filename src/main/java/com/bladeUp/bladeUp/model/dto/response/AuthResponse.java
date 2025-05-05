package com.bladeUp.bladeUp.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
public class AuthResponse {
    private Long user_id;
    private String email;
    private String fullName;
    private boolean isActive;


    public AuthResponse(Long user_id, String email, String fullName, boolean isActive) {
        this.user_id = user_id;
        this.email = email;
        this.fullName = fullName;
        this.isActive = isActive;
    }


}
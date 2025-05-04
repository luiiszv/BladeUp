package com.bladeUp.bladeUp.model.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientResponseDTO {
    private Long id;
    private String email;
    private String fullName;
    private String phone;
    private Integer loyaltyPoints;
    private Boolean isActive;
}
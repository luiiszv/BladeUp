package com.bladeUp.bladeUp.model.dto.request;

import jakarta.validation.constraints.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientRequestDTO {
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 8)
    private String password;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Pattern(regexp = "^\\+?[0-9]{10,15}$")
    private String phone;

    @PositiveOrZero
    private Integer loyaltyPoints = 0;
}
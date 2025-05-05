package com.bladeUp.bladeUp.model.dto.request;


import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public class ClientRequestDto {
    private Long user_id;

    @NotBlank(message = "FirstName is required")
    private String firstName;
    @NotBlank(message = "lastName is required")
    private String lastName;
    @NotBlank(message = "email is required")
    private String email;

    private Date birthdate;
    @NotBlank(message = "Password is required")
    private String password;
    private Boolean active;
    @NotBlank(message = "PhoneNumber is required")
    private String phoneNumber;
    private Integer loyaltyPoints = 0;
    private String preferredServices;
    private Boolean isActive;

    private String address;

    public ClientRequestDto() {
    }

    public ClientRequestDto(Integer loyaltyPoints, String preferredServices, Boolean isActive) {
        this.loyaltyPoints = loyaltyPoints;
        this.preferredServices = preferredServices;
        this.isActive = isActive;
    }

    // Getters
    public Long getUser_id() {
        return user_id;
    }

    public String getFirstName() {
        return firstName;
    }


    public String getLastName() {
        return lastName;
    }


    public String getEmail() {
        return email;
    }


    public Date getBirthdate() {
        return birthdate;
    }


    public String getPassword() {
        return password;
    }


    public Boolean getActive() {
        return active;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }


    public Integer getLoyaltyPoints() {
        return loyaltyPoints;
    }


    public String getPreferredServices() {
        return preferredServices;
    }


    public Boolean getIsActive() {
        return isActive;
    }


    public String getAddress() {
        return address;
    }

    //Setter

    public void setPassword(String password) {
        this.password = password;
    }


}
package com.bladeUp.bladeUp.model.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalTime;
import java.util.Date;

public class BarberRequestDto {

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
    private String shopAddress;
    private Integer yearsExperience;
    private LocalTime startTime; // Inicio de turno
    private LocalTime endTime;
    private Boolean isActive;

    private String address;

    public BarberRequestDto() {
    }

    public BarberRequestDto(Integer yearsExperience, LocalTime startTime, LocalTime endTime, String shopAddress) {
        this.yearsExperience = yearsExperience;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isActive = isActive;
        this.shopAddress = shopAddress;
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


    public Boolean getIsActive() {
        return isActive;
    }

    public String getShopAddress() {
        return shopAddress;
    }


    public String getAddress() {
        return address;
    }

    //Setter

    public void setPassword(String password) {
        this.password = password;
    }
}

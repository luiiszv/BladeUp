package com.bladeUp.bladeUp.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BarberResponseDto {
    private Long user_id;
    private String firstName;
    private String lastName;
    private String email;
    private String shopAddress;
    private Integer yearsExperience;
    private LocalTime startTime; // Inicio de turno
    private LocalTime endTime;

    public BarberResponseDto(Long user_id, String firstName, String lastName, String email, Integer yearsExperience, LocalTime startTime, LocalTime endTime) {
        this.user_id = user_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.shopAddress = shopAddress;
        this.yearsExperience = yearsExperience;
        this.startTime = startTime;
        this.endTime = endTime;

    }

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

    public String getShopAddress() {
        return shopAddress;
    }

    public Integer getYearsExperience() {
        return yearsExperience;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }


    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public void setYearsExperience(Integer yearsExperience) {
        this.yearsExperience = yearsExperience;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

}

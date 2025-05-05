package com.bladeUp.bladeUp.model.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ClientResponseDTO implements Serializable {

    private Long user_id;
    private String firstName;
    private String lastName;
    private String email;
    private Integer loyaltyPoints = 0;
    private String preferredServices;


    public ClientResponseDTO() {
    }

    public ClientResponseDTO(Long user_id, String firstName, String lastName, String email, Integer loyaltyPoints, String preferredServices) {
        this.user_id = user_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.loyaltyPoints = loyaltyPoints;
        this.preferredServices = preferredServices;
    }


    public String getName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Integer getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public String getPreferredServices() {
        return preferredServices;
    }

    public void setName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLoyaltyPoints(Integer loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    public void setPreferredServices(String preferredServices) {
        this.preferredServices = preferredServices;
    }


}
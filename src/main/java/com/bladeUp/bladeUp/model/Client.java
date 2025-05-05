package com.bladeUp.bladeUp.model;

import jakarta.persistence.*;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter


@PrimaryKeyJoinColumn(name = "client_id") // PK que es FK a User
public class Client extends User { // Hereda de User

    @Column(name = "loyalty_points")
    private Integer loyaltyPoints;
    private String preferredServices;

    private String address;  //La diorecion es privada del usuario

    //Constructior
    public Client(Integer loyaltyPoints, String preferredServices, String address) {
        this.loyaltyPoints = loyaltyPoints;
        this.preferredServices = preferredServices;
        this.address = address;
    }

    public Client() {

    }

    //Getters
    public String getAddress() {
        return address;
    }

    public String getPreferredServices() {
        return preferredServices;
    }

    public Integer getLoyaltyPoints() {
        return loyaltyPoints;
    }


    // Setters
    public void setAddress(String address) {
        this.address = address;
    }

    public void setPreferredServices(String preferredServices) {
        this.preferredServices = preferredServices;
    }

    public void setLoyaltyPoints(Integer loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return "";
    }
}
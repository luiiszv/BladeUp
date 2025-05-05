package com.bladeUp.bladeUp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) // Estrategia JOINED para herencia en BD
@Getter
@Setter // Lombok: genera getters y setters
@NoArgsConstructor // Lombok: constructor sin argumentos

public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incremental
    private Long user_id;


    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(unique = true, nullable = false) // Email único en la BD
    private String email;

    private Date birthdate;
    @Column(nullable = false)
    private String password;
    private Boolean active;
    @Column(nullable = false)
    private String phoneNumber;

    // Método conveniente para verificar estado
    public boolean isActive() {
        return Boolean.TRUE.equals(active);
    }


    //Getters
    public String getFirstName() {
        return firstName;
    }

    public Long getUserId() {
        return user_id;
    }


    public Date getBirthdate() {
        return birthdate;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getIsActive() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    //Setters

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setIsActive(Boolean active) {
        this.active = active;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }


}
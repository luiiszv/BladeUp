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


    //    @NotBlank(message = "First name is required")  validaciones mas adelante si sabe
    //    @Email(message = "Email should be valid") // Validación formato email
    //    @Size(min = 8, message = "Password must be at least 8 characters") // Mínimo 8 caracteres

}
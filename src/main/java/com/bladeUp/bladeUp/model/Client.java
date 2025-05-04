package com.bladeUp.bladeUp.model;

import jakarta.persistence.*;
import lombok.Data;

// Archivo: Client.java (Modelo para clientes)

import java.util.*;

import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor

@PrimaryKeyJoinColumn(name = "client_id") // PK que es FK a User
public class Client extends User { // Hereda de User






    @Column(name = "loyalty_points")
    private Integer loyaltyPoints = 0; // Puntos de fidelidad (inicia en 0)
}
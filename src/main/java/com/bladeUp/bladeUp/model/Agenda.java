package com.bladeUp.bladeUp.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Agenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    private List<String> horariosDisponibles;

    public Agenda() {}

    // Getters y Setters
}
package com.bladeUp.bladeUp.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
public class Agenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Barbero barbero; 

    private LocalDate fecha; 
    private LocalTime horaInicio; 
    private LocalTime horaFin;    

    private boolean disponible; 

    public Agenda() {}

    public Agenda(Barbero barbero, LocalDate fecha, LocalTime horaInicio, LocalTime horaFin, boolean disponible) {
        this.barbero = barbero;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.disponible = disponible;
    }


    public Long getId() {
        return id;
    }

    public Barbero getBarbero() {
        return barbero;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}
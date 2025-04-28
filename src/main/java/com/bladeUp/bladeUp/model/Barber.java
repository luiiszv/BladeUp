package com.bladeUp.bladeUp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
public class Barber extends User {

    private String especialidad;
    private Integer experienciaAnios;
    private Double calificacionPromedio;
    private String descripcion;
    private String fotoPerfilUrl;
}
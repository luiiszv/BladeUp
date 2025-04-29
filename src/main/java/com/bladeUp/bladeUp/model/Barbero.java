package com.bladeUp.bladeUp.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Barbero extends Usuario {

    @OneToMany(mappedBy = "barbero")
    private List<Solicitud> solicitudesAtendidas;

    public Barbero() {
    }

    public Barbero(String nombre, String correo, String telefono, String password, String rol) {
        super(nombre, correo, telefono, password, rol);
    }

  
}
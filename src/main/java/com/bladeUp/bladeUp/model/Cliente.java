package com.bladeUp.bladeUp.model;

import jakarta.persistence.Entity;

@Entity
public class Cliente extends Usuario {
    public Cliente() {
    }

    public Cliente(String nombre, String correo, String telefono, String password, String rol) {
        super(nombre, correo, telefono, password, rol);
    }
}
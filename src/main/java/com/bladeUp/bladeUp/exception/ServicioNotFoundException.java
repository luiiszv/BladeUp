package com.bladeUp.bladeUp.exception;

public class ServicioNotFoundException extends RuntimeException {
    public ServicioNotFoundException(Long id) {
        super("Servicio con ID " + id + " no encontrado.");
    }
}

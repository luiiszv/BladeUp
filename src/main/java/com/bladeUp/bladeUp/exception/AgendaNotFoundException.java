package com.bladeUp.bladeUp.exception;

public class AgendaNotFoundException extends RuntimeException {
    public AgendaNotFoundException(Long id) {
        super("Bloque de agenda con ID " + id + " no encontrado.");
    }
}
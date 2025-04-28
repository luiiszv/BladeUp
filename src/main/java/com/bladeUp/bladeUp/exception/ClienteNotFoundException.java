package com.bladeUp.bladeUp.exception;

public class ClienteNotFoundException extends RuntimeException {
    public ClienteNotFoundException(Long id) {
        super("Cliente con ID " + id + " no encontrado.");
    }
}
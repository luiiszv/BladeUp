package com.bladeUp.bladeUp.coresponsability;

import com.bladeUp.bladeUp.interfaces.ValidadorSolicitud;
import com.bladeUp.bladeUp.model.Solicitud;

public class ValidadorClienteDisponible implements ValidadorSolicitud {

    private ValidadorSolicitud siguiente;

    @Override
    public void setSiguiente(ValidadorSolicitud siguiente) {
        this.siguiente = siguiente;
    }

    @Override
    public void validar(Solicitud solicitud) {
        if (solicitud.getCliente() == null) {
            throw new RuntimeException("El cliente no está asignado.");
        }

        // Si pasa, continúa
        if (siguiente != null) {
            siguiente.validar(solicitud);
        }
    }
}

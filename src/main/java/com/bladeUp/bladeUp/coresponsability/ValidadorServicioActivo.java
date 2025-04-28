package com.bladeUp.bladeUp.coresponsability;

import com.bladeUp.bladeUp.interfaces.ValidadorSolicitud;
import com.bladeUp.bladeUp.model.Solicitud;

public class ValidadorServicioActivo implements ValidadorSolicitud {

    private ValidadorSolicitud siguiente;

    @Override
    public void setSiguiente(ValidadorSolicitud siguiente) {
        this.siguiente = siguiente;
    }

    @Override
    public void validar(Solicitud solicitud) {
        if (solicitud.getServicio() == null) {
            throw new RuntimeException("El servicio no está activo.");
        }

        // Si pasa, continúa
        if (siguiente != null) {
            siguiente.validar(solicitud);
        }
    }
}
package com.bladeUp.bladeUp.coresponsability;

import com.bladeUp.bladeUp.interfaces.ValidadorSolicitud;
import com.bladeUp.bladeUp.model.Solicitud;

public class ValidadorDisponibilidadBarbero implements ValidadorSolicitud {

    private ValidadorSolicitud siguiente;

    @Override
    public void setSiguiente(ValidadorSolicitud siguiente) {
        this.siguiente = siguiente;
    }

    @Override
    public void validar(Solicitud solicitud) {
    
        if (solicitud.getBarbero() == null) {
            throw new RuntimeException("El barbero no está asignado.");
        }

     
        if (siguiente != null) {
            siguiente.validar(solicitud);
        }
    }
}

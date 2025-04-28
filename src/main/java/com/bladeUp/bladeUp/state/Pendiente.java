package com.bladeUp.bladeUp.state;

import com.bladeUp.bladeUp.interfaces.EstadoSolicitud;
import com.bladeUp.bladeUp.model.Solicitud;

public class Pendiente implements EstadoSolicitud {

    @Override
    public void siguiente(Solicitud solicitud) {
        solicitud.setEstado(new Confirmada());
    }

    @Override
    public String getNombreEstado() {
        return "PENDIENTE";
    }
}
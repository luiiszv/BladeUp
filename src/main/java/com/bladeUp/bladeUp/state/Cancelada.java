package com.bladeUp.bladeUp.state;

import com.bladeUp.bladeUp.interfaces.EstadoSolicitud;
import com.bladeUp.bladeUp.model.Solicitud;

public class Cancelada implements EstadoSolicitud {

    @Override
    public void siguiente(Solicitud solicitud) {
    
    }

    @Override
    public String getNombreEstado() {
        return "CANCELADA";
    }
}

package com.bladeUp.bladeUp.state;

import com.bladeUp.bladeUp.interfaces.EstadoSolicitud;
import com.bladeUp.bladeUp.model.Solicitud;

public class Confirmada implements EstadoSolicitud {

    @Override
    public void siguiente(Solicitud solicitud) {
        solicitud.setEstado(new EnProceso());
    }

    @Override
    public String getNombreEstado() {
        return "CONFIRMADA";
    }
}

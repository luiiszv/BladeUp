package com.bladeUp.bladeUp.state;

import com.bladeUp.bladeUp.interfaces.EstadoSolicitud;
import com.bladeUp.bladeUp.model.Solicitud;

public class EnProceso implements EstadoSolicitud {

    @Override
    public void siguiente(Solicitud solicitud) {
        solicitud.setEstado(new Finalizada());
    }

    @Override
    public String getNombreEstado() {
        return "EN_PROCESO";
    }
}
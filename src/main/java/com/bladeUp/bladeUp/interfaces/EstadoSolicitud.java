package com.bladeUp.bladeUp.interfaces;

import com.bladeUp.bladeUp.model.Solicitud;

public interface EstadoSolicitud {
    void siguiente(Solicitud solicitud);

    String getNombreEstado();
}

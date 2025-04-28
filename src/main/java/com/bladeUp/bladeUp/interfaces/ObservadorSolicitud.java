package com.bladeUp.bladeUp.interfaces;

import com.bladeUp.bladeUp.model.Solicitud;

public interface ObservadorSolicitud {
    void notificar(Solicitud solicitud);
}

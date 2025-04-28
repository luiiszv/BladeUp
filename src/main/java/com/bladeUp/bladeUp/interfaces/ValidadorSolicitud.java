package com.bladeUp.bladeUp.interfaces;

import com.bladeUp.bladeUp.model.Solicitud;

public interface ValidadorSolicitud {
    void setSiguiente(ValidadorSolicitud siguiente);

    void validar(Solicitud solicitud);
}
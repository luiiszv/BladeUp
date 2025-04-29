package com.bladeUp.bladeUp.observer;

import com.bladeUp.bladeUp.interfaces.ObservadorSolicitud;
import com.bladeUp.bladeUp.model.Solicitud;

public class ClienteObservador implements ObservadorSolicitud {

    @Override
    public void notificar(Solicitud solicitud) {
        System.out.println("Notificación: Estimado " + solicitud.getCliente().getNombre() +
                ", su solicitud ha sido " + solicitud.getEstadoActual().toLowerCase() + ".");
    }
}

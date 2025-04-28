package com.bladeUp.bladeUp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.bladeUp.bladeUp.auth.RolValidator;
import com.bladeUp.bladeUp.model.Solicitud;
import com.bladeUp.bladeUp.observer.ClienteObservador;
import com.bladeUp.bladeUp.repository.SolicitudRepository;
import com.bladeUp.bladeUp.state.Cancelada;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

@RestController
@RequestMapping("/api/solicitudes")
public class SolicitudController {

    @Autowired
    private SolicitudRepository solicitudRepository;

    @PostMapping
    public Object crearSolicitud(@RequestBody Solicitud solicitud, HttpServletRequest request) {
        if (!RolValidator.tieneRol(request, "CLIENTE")) {
            return "Acceso denegado: solo Clientes pueden crear solicitudes ❌";
        }
        return solicitudRepository.save(solicitud);
    }

    @GetMapping
    public List<Solicitud> listarSolicitudes() {
        return solicitudRepository.findAll();
    }

    @GetMapping("/{id}")
    public Solicitud obtenerSolicitudPorId(@PathVariable Long id) {
        return solicitudRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));
    }

    @PutMapping("/{id}")
    public Solicitud actualizarSolicitud(@PathVariable Long id, @RequestBody Solicitud solicitudActualizada) {
        Solicitud solicitud = solicitudRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));

        solicitud.setHorario(solicitudActualizada.getHorario());
        solicitud.setEstado(solicitudActualizada.getEstado());

        return solicitudRepository.save(solicitud);
    }

    @PutMapping("/cambiar-estado/{id}")
    public String cambiarEstado(@PathVariable Long id) {
        Solicitud solicitud = solicitudRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));

        solicitud.agregarObservador(new ClienteObservador()); // 🔔 agregamos observador antes de cambiar estado
        solicitud.cambiarEstado(); // cambia de Pendiente → Confirmada → En Proceso → Finalizada
        solicitudRepository.save(solicitud);

        return "Estado actualizado a: " + solicitud.getEstadoActual();
    }

    @PutMapping("/cancelar/{id}")
    public String cancelarSolicitud(@PathVariable Long id) {
        Solicitud solicitud = solicitudRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));

        // Agregar un observador al cliente antes de cancelar
        solicitud.agregarObservador(new ClienteObservador());

        solicitud.cancelar(); // Aquí usamos el método cancelar que notifica
        solicitudRepository.save(solicitud);

        return "Solicitud cancelada exitosamente.";
    }

    @DeleteMapping("/{id}")
    public void eliminarSolicitud(@PathVariable Long id) {
        solicitudRepository.deleteById(id);
    }
}

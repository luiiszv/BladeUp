package com.bladeUp.bladeUp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.bladeUp.bladeUp.auth.RolValidator;
import com.bladeUp.bladeUp.coresponsability.ValidadorClienteDisponible;
import com.bladeUp.bladeUp.coresponsability.ValidadorDisponibilidadBarbero;
import com.bladeUp.bladeUp.coresponsability.ValidadorServicioActivo;
import com.bladeUp.bladeUp.exception.ClienteNotFoundException;
import com.bladeUp.bladeUp.exception.ServicioNotFoundException;
import com.bladeUp.bladeUp.interfaces.ValidadorSolicitud;
import com.bladeUp.bladeUp.model.Agenda;
import com.bladeUp.bladeUp.model.Solicitud;
import com.bladeUp.bladeUp.observer.ClienteObservador;
import com.bladeUp.bladeUp.repository.AgendaRepository;
import com.bladeUp.bladeUp.repository.ClienteRepository;
import com.bladeUp.bladeUp.repository.ServicioRepository;
import com.bladeUp.bladeUp.repository.SolicitudRepository;
import com.bladeUp.bladeUp.state.Cancelada;

import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/solicitudes")
public class SolicitudController {

    @Autowired
    private SolicitudRepository solicitudRepository;

    @Autowired
    private AgendaRepository agendaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ServicioRepository servicioRepository;

    @PostMapping("/crear")
    public String crearSolicitud(@RequestBody Solicitud solicitud) {
        // Extraemos datos
        Long barberoId = solicitud.getBarbero().getId();
        String horario = solicitud.getHorario(); // ejemplo: "10:00"
        LocalDate fechaHoy = LocalDate.now(); // O podría venir en el RequestBody

        // Buscar si existe un bloque disponible en la Agenda
        List<Agenda> bloquesDisponibles = agendaRepository.findByBarberoIdAndFecha(barberoId, fechaHoy);

        Agenda bloqueCorrecto = bloquesDisponibles.stream()
                .filter(a -> a.isDisponible() && a.getHoraInicio().toString().equals(horario))
                .findFirst()
                .orElse(null);

        if (bloqueCorrecto == null) {
            return "No hay horario disponible para agendar.";
        }

        // Marcar como no disponible
        bloqueCorrecto.setDisponible(false);
        agendaRepository.save(bloqueCorrecto);

        // Crear la solicitud en estado PENDIENTE
        solicitudRepository.save(solicitud);

        return "Solicitud creada correctamente y horario reservado.";
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

        // Crear validadores
        ValidadorSolicitud validador1 = new ValidadorDisponibilidadBarbero();
        ValidadorSolicitud validador2 = new ValidadorClienteDisponible();
        ValidadorSolicitud validador3 = new ValidadorServicioActivo();

        // Encadenarlos
        validador1.setSiguiente(validador2);
        validador2.setSiguiente(validador3);

        // Validar antes de cambiar estado
        validador1.validar(solicitud);

        solicitud.agregarObservador(new ClienteObservador());
        solicitud.cambiarEstado();
        solicitudRepository.save(solicitud);

        return "Estado actualizado a: " + solicitud.getEstadoActual();
    }

    @PutMapping("/cancelar/{id}")
    public String cancelarSolicitud(@PathVariable Long id) {
        Solicitud solicitud = solicitudRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));

        // Liberar el bloque de agenda
        Long barberoId = solicitud.getBarbero().getId();
        String horario = solicitud.getHorario();
        LocalDate fechaHoy = LocalDate.now(); // O la fecha que uses para las citas

        List<Agenda> bloques = agendaRepository.findByBarberoIdAndFecha(barberoId, fechaHoy);

        // Buscar el bloque específico
        Agenda bloque = bloques.stream()
                .filter(a -> !a.isDisponible() && a.getHoraInicio().toString().equals(horario))
                .findFirst()
                .orElse(null);

        if (bloque != null) {
            bloque.setDisponible(true); //iberamos
            agendaRepository.save(bloque);
        }

        // Cancelar la solicitud
        solicitud.setEstado(new Cancelada());
        solicitud.setEstadoActual("CANCELADA");
        solicitudRepository.save(solicitud);

        return "✅ Solicitud cancelada y horario liberado exitosamente.";
    }

    @DeleteMapping("/{id}")
    public void eliminarSolicitud(@PathVariable Long id) {
        solicitudRepository.deleteById(id);
    }

    @PostMapping("/agendar")
    public String agendarDesdeHorario(@RequestParam Long agendaId, @RequestParam Long clienteId,
            @RequestParam Long servicioId) {
        Agenda agenda = agendaRepository.findById(agendaId)
                .orElseThrow(() -> new RuntimeException("Bloque de agenda no encontrado"));

        if (!agenda.isDisponible()) {
            return "Horario no disponible, ya fue reservado.";
        }

        // Crear nueva solicitud
        Solicitud solicitud = new Solicitud();
        solicitud.setCliente(clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ClienteNotFoundException(clienteId)));
        solicitud.setBarbero(agenda.getBarbero());
        solicitud.setServicio(servicioRepository.findById(servicioId)
                .orElseThrow(() -> new ServicioNotFoundException(servicioId)));
        solicitud.setHorario(agenda.getHoraInicio().toString()); // Guardamos el horario exacto

        solicitudRepository.save(solicitud);

        // Marcar el horario como no disponible
        agenda.setDisponible(false);
        agendaRepository.save(agenda);

        return "Solicitud creada y horario reservado exitosamente.";
    }

}

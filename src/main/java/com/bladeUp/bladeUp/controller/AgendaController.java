package com.bladeUp.bladeUp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.bladeUp.bladeUp.model.Agenda;
import com.bladeUp.bladeUp.repository.AgendaRepository;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/agenda")
public class AgendaController {

    @Autowired
    private AgendaRepository agendaRepository;

    // Ver la agenda de un barbero en una fecha
    @GetMapping("/{barberoId}/{fecha}")
    public List<Agenda> verAgendaBarbero(@PathVariable Long barberoId, @PathVariable String fecha) {
        LocalDate fechaLocal = LocalDate.parse(fecha); // ejemplo: "2025-04-30"
        return agendaRepository.findByBarberoIdAndFecha(barberoId, fechaLocal);
    }

    // Crear disponibilidad para un barbero
    @PostMapping("/crear")
    public Agenda crearAgenda(@RequestBody Agenda agenda) {
        return agendaRepository.save(agenda);
    }

    @GetMapping("/disponibles/{barberoId}/{fecha}")
public List<Agenda> verHorariosDisponibles(@PathVariable Long barberoId, @PathVariable String fecha) {
    LocalDate fechaLocal = LocalDate.parse(fecha); // Ejemplo: "2025-05-01"
    
    List<Agenda> bloques = agendaRepository.findByBarberoIdAndFecha(barberoId, fechaLocal);
    
    // Filtrar solo los disponibles
    return bloques.stream()
            .filter(Agenda::isDisponible)
            .toList();
}

}
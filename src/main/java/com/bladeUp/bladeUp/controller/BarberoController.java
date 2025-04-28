package com.bladeUp.bladeUp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.bladeUp.bladeUp.model.Barbero;
import com.bladeUp.bladeUp.repository.BarberoRepository;

import java.util.List;

@RestController
@RequestMapping("/api/barberos")
public class BarberoController {

    @Autowired
    private BarberoRepository barberoRepository;

    @PostMapping
    public Barbero crearBarbero(@RequestBody Barbero barbero) {
        return barberoRepository.save(barbero);
    }

    @GetMapping
    public List<Barbero> listarBarberos() {
        return barberoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Barbero obtenerBarberoPorId(@PathVariable Long id) {
        return barberoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Barbero no encontrado"));
    }

    @PutMapping("/{id}")
    public Barbero actualizarBarbero(@PathVariable Long id, @RequestBody Barbero barberoActualizado) {
        Barbero barbero = barberoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Barbero no encontrado"));

        barbero.setNombre(barberoActualizado.getNombre());
        barbero.setCorreo(barberoActualizado.getCorreo());
        barbero.setTelefono(barberoActualizado.getTelefono());

        return barberoRepository.save(barbero);
    }

    @DeleteMapping("/{id}")
    public void eliminarBarbero(@PathVariable Long id) {
        barberoRepository.deleteById(id);
    }
}
package com.bladeUp.bladeUp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.bladeUp.bladeUp.auth.RolValidator;
import com.bladeUp.bladeUp.exception.ServicioNotFoundException;
import com.bladeUp.bladeUp.model.Servicio;
import com.bladeUp.bladeUp.repository.ServicioRepository;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

@RestController
@RequestMapping("/api/servicios")
public class ServicioController {

    @Autowired
    private ServicioRepository servicioRepository;

    @PostMapping
    public Object crearServicio(@RequestBody Servicio servicio, HttpServletRequest request) {
        if (!RolValidator.tieneRol(request, "BARBERO")) {
            return "Acceso denegado: solo Barberos pueden crear servicios";
        }
        return servicioRepository.save(servicio);
    }

    @GetMapping
    public List<Servicio> listarServicios() {
        return servicioRepository.findAll();
    }

    @GetMapping("/{id}")
    public Servicio obtenerServicioPorId(@PathVariable Long id) {
        return servicioRepository.findById(id)
                .orElseThrow(() -> new ServicioNotFoundException(id));
    }

    @PutMapping("/{id}")
    public Servicio actualizarServicio(@PathVariable Long id, @RequestBody Servicio servicioActualizado) {
        Servicio servicio = servicioRepository.findById(id)
                .orElseThrow(() -> new ServicioNotFoundException(id));

        servicio.setNombre(servicioActualizado.getNombre());
        servicio.setDescripcion(servicioActualizado.getDescripcion());
        servicio.setPrecio(servicioActualizado.getPrecio());
        servicio.setDuracionMinutos(servicioActualizado.getDuracionMinutos());

        return servicioRepository.save(servicio);
    }

    @DeleteMapping("/{id}")
    public void eliminarServicio(@PathVariable Long id) {
        servicioRepository.deleteById(id);
    }
}
package com.bladeUp.bladeUp.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bladeUp.bladeUp.interfaces.EstadoSolicitud;
import com.bladeUp.bladeUp.interfaces.ObservadorSolicitud;
import com.bladeUp.bladeUp.state.Pendiente;

@Entity
public class Solicitud {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private Barbero barbero;

    @ManyToOne
    private Servicio servicio;

    private String horario;
    private Date fechaCreacion;


    @Transient 
    private EstadoSolicitud estado;
    private String estadoActual;

     @Transient
    private List<ObservadorSolicitud> observadores = new ArrayList<>();

    public Solicitud() {
        this.fechaCreacion = new Date();
        this.estado = new Pendiente();
        this.estadoActual = estado.getNombreEstado();
    }

    public Solicitud(Cliente cliente, Barbero barbero, Servicio servicio, String horario) {
        this();
        this.cliente = cliente;
        this.barbero = barbero;
        this.servicio = servicio;
        this.horario = horario;
    }

    public void cambiarEstado() {
        estado.siguiente(this);
        this.estadoActual = estado.getNombreEstado();
        notificarObservadores();
    }

    public void cancelar() {
        this.estadoActual = "CANCELADA";
        notificarObservadores();
    }

    public void agregarObservador(ObservadorSolicitud observador) {
        observadores.add(observador);
    }

    private void notificarObservadores() {
        for (ObservadorSolicitud observador : observadores) {
            observador.notificar(this);
        }
    }

  
    public Long getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Barbero getBarbero() {
        return barbero;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public String getHorario() {
        return horario;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public String getEstadoActual() {
        return estadoActual;
    }

    
    public void setId(Long id) {
        this.id = id;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setBarbero(Barbero barbero) {
        this.barbero = barbero;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public void setEstado(EstadoSolicitud estado) {
        this.estado = estado;
    }

    public void setEstadoActual(String estadoActual) {
        this.estadoActual = estadoActual;
    }

    public EstadoSolicitud getEstado() {
        return estado;
    }
}

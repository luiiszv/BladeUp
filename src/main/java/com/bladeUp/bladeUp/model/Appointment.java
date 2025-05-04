package com.bladeUp.bladeUp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appointment_id;

    @Column(nullable = false)
    private LocalDateTime startTime; // Hora inicio cita
    private LocalDateTime endTime;   // Hora fin cita

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AppointmentStatus status; // Estado de la cita

    @OneToOne
    @JoinColumn(name = "appointment_request_id") // FK de AppointmentRequest
    private AppointmentRequest appointmentRequest; // Relación con la solicitud



    // Posibles estados de una cita
    public enum AppointmentStatus {
        SCHEDULED,   // Agendada programada
        CONFIRMED,   // Confirmada
        COMPLETED,   // Completada
        CANCELLED,   // Cancelada
        NO_SHOW      // No se presentó
    }
}
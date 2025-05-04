package com.bladeUp.bladeUp.model;

import jakarta.persistence.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class AppointmentRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appointment_request_id;

    // Cliente que solicita

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    // Barbero a quien se le solicita

    @ManyToOne(optional = false)  //No NULL
    @JoinColumn(name = "barber_id")
    private Barber barber;

    @OneToOne(mappedBy = "appointmentRequest", cascade = CascadeType.ALL)
    private Appointment appointment;


    private LocalDateTime requestedStartTime;
    private String clientComment;
    private String barberResponse;


    @Enumerated(EnumType.STRING)

    private RequestStatus status = RequestStatus.PENDING;

    public enum RequestStatus {
        PENDING,
        APPROVED,
        REJECTED,
        CANCELLED
    }
}
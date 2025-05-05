package com.bladeUp.bladeUp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalTime;
import java.util.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Barber extends User { // Hereda de User

    private Set<String> specialties = new HashSet<>();

    @Column(name = "schedule_work")
    private String scheduleWork;

    @Column(name = "shop_address", nullable = false)
    private String shopAddress;

    @Column(name = "years_experience", nullable = false)
    private Integer yearsExperience;

    private String biography;

    private LocalTime startTime; // Inicio de turno
    private LocalTime endTime;   // Fin de turno

    private Boolean isAvailable = true;

    // Relación ManyToMany con servicios ofrecidos
    @ManyToMany
    @JoinTable(
            name = "barber_service",
            joinColumns = @JoinColumn(name = "barber_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id")
    )
    private List<Service> services = new ArrayList<>();

    // Si quieres ver las solicitudes del barbero, puedes agregar:
    @OneToMany(mappedBy = "barber", cascade = CascadeType.ALL)
    private List<AppointmentRequest> appointmentRequests = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return "";
    }
}
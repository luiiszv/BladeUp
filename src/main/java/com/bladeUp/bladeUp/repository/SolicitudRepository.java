package com.bladeUp.bladeUp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bladeUp.bladeUp.model.Solicitud;

public interface SolicitudRepository extends JpaRepository<Solicitud, Long> {
}

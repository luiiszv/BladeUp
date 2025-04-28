package com.bladeUp.bladeUp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bladeUp.bladeUp.model.Barbero;

public interface BarberoRepository extends JpaRepository<Barbero, Long> {
    Barbero findByCorreo(String correo);
}


package com.bladeUp.bladeUp.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bladeUp.bladeUp.model.Agenda;

public interface AgendaRepository extends JpaRepository<Agenda, Long> {
    List<Agenda> findByBarberoIdAndFecha(Long barberoId, LocalDate fecha);
}

package com.bladeUp.bladeUp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bladeUp.bladeUp.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Cliente findByCorreo(String correo); 
}

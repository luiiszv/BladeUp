package com.bladeUp.bladeUp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bladeUp.bladeUp.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}

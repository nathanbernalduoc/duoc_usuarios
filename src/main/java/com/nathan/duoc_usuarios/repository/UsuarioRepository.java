package com.nathan.duoc_usuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nathan.duoc_usuarios.dto.UsuarioDto;

public interface UsuarioRepository extends JpaRepository<UsuarioDto, Long> {
    
}

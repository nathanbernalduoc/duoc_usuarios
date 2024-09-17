package com.nathan.duoc_usuarios.service;

import java.util.List;
import java.util.Optional;

import com.nathan.duoc_usuarios.dto.UsuarioDto;

public interface  UsuarioService {

    List<UsuarioDto> getAllUsuarios();
    Optional<UsuarioDto> getUsuarioById(Long id);
    
    UsuarioDto createUsuario(UsuarioDto usuario);
    UsuarioDto updateUsuario(Long id, UsuarioDto usuario);
    void deleteUsuario(Long id);

}

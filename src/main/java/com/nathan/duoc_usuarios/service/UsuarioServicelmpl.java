package com.nathan.duoc_usuarios.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nathan.duoc_usuarios.dto.UsuarioDto;
import com.nathan.duoc_usuarios.repository.UsuarioRepository;

@Service
public class UsuarioServicelmpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Override
    public List<UsuarioDto> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<UsuarioDto> getUsuarioById(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public Optional<UsuarioDto> getUsuarioByAlias(String alias) {
        List<UsuarioDto> usuarios = usuarioRepository.findAll();
        Optional<UsuarioDto> usuarioSel = null;
        for(UsuarioDto usuario: usuarios) {
            if (usuario.getAlias().equals(alias)) {
                usuarioSel = Optional.of(usuario);
            }
        }
        return usuarioSel;
    }

    @Override
    public UsuarioDto createUsuario(UsuarioDto usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public UsuarioDto updateUsuario(Long id, UsuarioDto usuario) {
        if (usuarioRepository.existsById(id)) {
            usuario.setId(id);
            return usuarioRepository.save(usuario);
        } else {
            return null;
        }
    }

    @Override
    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}

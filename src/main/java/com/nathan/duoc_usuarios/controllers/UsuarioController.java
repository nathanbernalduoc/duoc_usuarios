package com.nathan.duoc_usuarios.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Optional;

import com.nathan.duoc_usuarios.dto.LoginDto;
import com.nathan.duoc_usuarios.dto.ResultDto;
import com.nathan.duoc_usuarios.dto.UsuarioDto;
import com.nathan.duoc_usuarios.service.UsuarioService;


@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping({"/usuario/add", "/usuario/add/"})
    public ResultDto setUsuario(@RequestBody UsuarioDto usuario) {
        List<UsuarioDto> usuarios = usuarioService.getAllUsuarios();
        ResultDto result = new ResultDto("error", "El alias utilizado para la creación del usuario ya está en uso,");
        UsuarioDto usuarioOut = new UsuarioDto();
        int sw = 0;
        for(UsuarioDto u: usuarios) {
            if (u.getAlias().equals(usuario.getAlias())) {
                sw = 1;
            }
        }
        if (sw == 0) {
            usuarioOut = usuarioService.createUsuario(usuario);
            result = new ResultDto("success", "Usuario creado exitosamente", usuarioOut);
        }
        return result;
    }

    @GetMapping({"/usuario/list", "/usuario/list/"})
    public List<UsuarioDto> getUsuarios() {
        return usuarioService.getAllUsuarios();
    }
    
    @GetMapping({"/usuario/id/{usuarioId}"})
    public Optional<UsuarioDto> getUsuarioById(@PathVariable Long usuarioId) {
        return usuarioService.getUsuarioById(usuarioId);
    }

    @GetMapping({"/usuario/alias/{alias}"})
    public ResultDto getUsuarioAlias(@PathVariable String alias) {
        ResultDto result = new ResultDto("error", "No se ha encontrado el alias consultado.");
        List<UsuarioDto> usuarios = usuarioService.getAllUsuarios();
        for(UsuarioDto u: usuarios) {
            if (u.getAlias().equals(alias)) {
                result = new ResultDto("success", "Usuario encontrado", u);
            }
        }
        return result;
    }

    @PostMapping("/login")
    public ResultDto login(@RequestBody LoginDto login) {
        ResultDto result = this.getUsuarioAlias(login.getAlias());
        UsuarioDto usuario = result.getUsuario();
        if (usuario != null) {
            System.out.println("Usuario "+usuario.getAlias());
            if (usuario.getPassword().equals(login.getPassword())) {
                result = new ResultDto("success", "Usuario "+usuario.getNombres()+" con acceso permitido.");
            } else {
                result = new ResultDto("error", "Contreaseña inválida! (en la realidad uno no entrega mucha información sobre esto)");
            }
            System.out.println("Contraseña "+usuario.getPassword());
        } else {
            result = new ResultDto("error", "Credenciales incorrectas.");
            System.out.println("No se encontró el usuario "+login.getAlias());
        }
        return result;
    }
    

    @PutMapping({"/usuario/update/{id}"})
    public UsuarioDto getUsuarioById(@PathVariable Long usuarioId, @RequestBody UsuarioDto usuario) {
        return usuarioService.updateUsuario(usuarioId, usuario);
    }

    @DeleteMapping({"/usuario/unset/{id}"})
    public void unsetUsuario(@PathVariable Long id) {
        usuarioService.deleteUsuario(id);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultDto IdNotFoundResponse(Exception e){
        return (new ResultDto("error", "Se ha detectado una excepción: "+e.getMessage()));
   }

}

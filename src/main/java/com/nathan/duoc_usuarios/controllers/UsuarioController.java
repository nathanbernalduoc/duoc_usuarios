package com.nathan.duoc_usuarios.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

import java.util.ArrayList;

import com.nathan.duoc_usuarios.dto.ResultDto;
import com.nathan.duoc_usuarios.dto.RolDto;
import com.nathan.duoc_usuarios.dto.UsuarioDto;

@RestController  
public class UsuarioController {

    private List<UsuarioDto> usuarios = new ArrayList<>();
    private List<RolDto> roles = new ArrayList<>();

    public UsuarioController() {
        // inicializao 5 roles
        roles.add(new RolDto(roles.size()+1, "Administrador"));
        roles.add(new RolDto(roles.size()+1, "Usuario editor"));
        roles.add(new RolDto(roles.size()+1, "Supervisor"));
        roles.add(new RolDto(roles.size()+1, "cliente on-line"));
        roles.add(new RolDto(roles.size()+1, "Cobrador"));
        // inicializo 5 usuarios
        usuarios.add(new UsuarioDto(1, roles.get(0), "nathanbernal", "test", "123", "Nathan", "Bernal", "Mi casa, doblando la esquina, Valparaíso.", 1));
        usuarios.add(new UsuarioDto(2, roles.get(1), "makitajimenez", "test$123", "af$", "Makita", "Jiménez", "Tu casa, al lado, Valparaíso.", 1));
        usuarios.add(new UsuarioDto(3, roles.get(2), "juanperez", "$1213", "d24", "Juan", "Pérez", "Tu casa, al lado, Santiago.", 1));
        usuarios.add(new UsuarioDto(4, roles.get(3), "miguelbernal", "$_3!1213", "da$#", "Miguel", "Bernal", "Tu casa, al lado, Quillota.", 1));
        usuarios.add(new UsuarioDto(5, roles.get(4), "silviavera", "$A1213", "wr4", "Silvia", "Veras", "Alameda 265, Santiago.", 1));
    }

    /*
     * Functions
     */

    private ResultDto removeRolId(int rolId) {
        ResultDto result = new ResultDto("error", "Rol "+rolId+": error al intentar eliminar el rol indicado.");
        for(RolDto rol: roles) {
            if (rol.getRolId() == rolId) {
                roles.remove(rol);
                result = new ResultDto("success", "Rol "+rolId+" eliminado correctamente.");
                break;
            }
        }
        return result;
    }

    private RolDto getRol(String nombre) {
        RolDto rolOut = new RolDto();
        for(RolDto rol: roles) {
            if (rol.getNombre().equals(nombre)) {
                rolOut = rol;
            }
        }
        return rolOut;
    }

    private RolDto getRolId(int rolId) {
        RolDto rolOut = new RolDto();
        for(RolDto rol: roles) {
            if (rol.getRolId() == rolId) {
                rolOut = rol;
            }
        }
        return rolOut;
    }


    private UsuarioDto getUsuario(String alias) {
        UsuarioDto usuarioOut = new UsuarioDto();
        for(UsuarioDto usuario: usuarios) {
            if (usuario.getAlias().equals(alias)) {
                usuarioOut = usuario;
                break;
            }
        }
        return usuarioOut;
    }

    private UsuarioDto getUsuarioId(int id) {
        UsuarioDto usuarioOut = new UsuarioDto();
        for(UsuarioDto usuario: usuarios) {
            if (usuario.getUsuarioId() == id) {
                usuarioOut = usuario;
                break;
            }
        }
        return usuarioOut;
    }

    private ResultDto removeUsuarioId(int id) {
        ResultDto result = new ResultDto("error", "Usuario "+id+": Error al intentar eliminar el usuario indicado.");
        for(UsuarioDto usuario: usuarios) {
            if (usuario.getUsuarioId() == id) {
                usuarios.remove(usuario);
                result = new ResultDto("success", "Usuario "+id+" eliminado correctamente.");
                break;
            }
        }
        return result;
    }

    /*
     * ENDPOINT
     */

    @GetMapping("/rol/list")
    public List<RolDto> getRoles() {
        return roles;
    }

    @GetMapping("/rol/add/")
    public ResultDto setRol(@RequestParam("nombre") String nombre) {
        RolDto rol = getRol(nombre);
        ResultDto result = new ResultDto();
        System.out.print(rol.getNombre());
        if (rol.getRolId() < 1) {
            roles.add(new RolDto(roles.size()+1, nombre));
            result = new ResultDto("success", "Nuevo rol "+roles.getLast().getRolId()+" creado.");
        } else {
            result = new ResultDto("error", "El nombre especificado ya existe.");
        }
        return result;
    }

    @GetMapping({"/rol/{id}"})
    public RolDto getRolDetail(@PathVariable int id) {
        return this.getRolId(id);
    }

    @GetMapping({"/rol/unset/{id}"})
    public ResultDto unsetRol(@PathVariable int id) {
        ResultDto result = new ResultDto("success", "Rol suprimido correctamente.");
        RolDto rol = this.getRolId(id); 
        if (rol.getRolId() > 0) {
            removeRolId(rol.getRolId());
        } else {
            result = new ResultDto("error", "Rol "+id+" no encontrado.");
        }
        return result;
    }

    @GetMapping({"/usuario/add", "/usuario/add/"})
    public ResultDto setUsuario(@RequestParam("rol") int rolId, @RequestParam("alias") String alias, @RequestParam("password") String password, @RequestParam("keyWord") String keyWord, @RequestParam("nombres") String nombres, @RequestParam("apellidos") String apellidos, @RequestParam("direccion") String direccion, @RequestParam("vigencia") int vigencia) {
        ResultDto result = new ResultDto();
        UsuarioDto usuario = getUsuario(alias);
        RolDto rol = getRolId(rolId);
        if (rol.getRolId() == 0) {
            result = new ResultDto("error", "Rol "+rolId+" no existe: No se puede crear usuario con un rol que no existe.");
        } else if (usuario.getUsuarioId() > 0) {
            result = new ResultDto("error", "El alias(username) utilizado ya se encuentra utilizado.");
        } else {
            usuario = new UsuarioDto(usuarios.size()+1, rol, alias, password, keyWord, nombres, apellidos, direccion, 1);
            usuarios.add(usuario);
            result = new ResultDto("success", "Nuevo usuario "+usuario.getUsuarioId()+" creado.", usuario);
        }
        return result;
    }

    @GetMapping({"/usuario/list", "/usuario/list/"})
    public List<UsuarioDto> getUsuarios() {
        return usuarios;
    }
    
    @GetMapping({"/usuario/alias/{alias}"})
    public UsuarioDto getUsuarioByAlias(@PathVariable String alias) {
        return getUsuario(alias);
    }

    @GetMapping({"/usuario/id/{usuarioId}"})
    public UsuarioDto getUsuarioById(@PathVariable int usuarioId) {
        return getUsuarioId(usuarioId);
    }

    @GetMapping({"/usuario/unset/{id}"})
    public ResultDto unsetUsuario(@PathVariable int id) {
        ResultDto result = new ResultDto("success", "Usuario suprimido correctamente.");
        UsuarioDto usuario = this.getUsuarioId(id); 
        if (usuario.getUsuarioId() > 0) {
            removeUsuarioId(usuario.getUsuarioId());
        } else {
            result = new ResultDto("error", "Usuario  "+id+" no encontrado.");
        }
        return result;
    }

    @GetMapping({"/login/", "/login"})
    public ResultDto login(@RequestParam("username") String username, @RequestParam("password") String password) {
        ResultDto result = new ResultDto("error", "Login no permitido, credenciales incorrectas.");
        UsuarioDto usuario = this.getUsuario(username); 
        if (usuario.getUsuarioId() > 0 && usuario.getPassword().equals(password)) {
            result = new ResultDto("success", "Credenciales correctas!");
        }
        return result;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultDto IdNotFoundResponse(Exception e){
        return (new ResultDto("error", "Se ha detectado una excepción: "+e.getMessage()));
   }

}

package com.nathan.duoc_usuarios.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nathan.duoc_usuarios.dto.LoginDto;
import com.nathan.duoc_usuarios.dto.UsuarioDto;
import com.nathan.duoc_usuarios.service.UsuarioService;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public CollectionModel<EntityModel<UsuarioDto>> getAllUsuarios() {

        List<UsuarioDto> usuarios = usuarioService.getAllUsuarios();
        List<EntityModel<UsuarioDto>> usuarioResource = 
            usuarios.stream().map(
                usuario -> EntityModel.of(
                    usuario,
                    WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(
                            this.getClass()
                        ).getUsuarioById(usuario.getId())
                    ).withSelfRel()
                )
            )
            .collect(Collectors.toList());

        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsuarios());
        CollectionModel<EntityModel<UsuarioDto>> resources = CollectionModel.of(usuarioResource, linkTo.withRel("usuarios"));

        return resources;
    }

    @GetMapping("/{id}")
    public EntityModel<UsuarioDto> getUsuarioById(@PathVariable Long id) {
        Optional<UsuarioDto> usuario = usuarioService.getUsuarioById(id);

        if (usuario.isPresent()) {
            return EntityModel.of(usuario.get(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getUsuarioById(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsuarios()).withRel("all-usuarios"));
        } else {
            throw new UsuarioNotFoundExcepcion("El usuario no ha sido encontrado");
        }

    }

    @PostMapping
    public EntityModel<UsuarioDto> createUsuario(@RequestBody UsuarioDto usuario) {
        UsuarioDto createdUsuario = usuarioService.createUsuario(usuario);

        return EntityModel.of(createdUsuario,
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getUsuarioById(createdUsuario.getId())).withSelfRel(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsuarios()).withRel("all-usuarios"));

    }

    @GetMapping("/alias/{alias}")
    public EntityModel<UsuarioDto> getUsuarioAlias(@PathVariable String alias) {

        Optional<UsuarioDto> usuario = usuarioService.getUsuarioByAlias(alias);

        if (usuario.isPresent()) {
            return EntityModel.of(usuario.get(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getUsuarioById(usuario.get().getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsuarios()).withRel("all-usuarios"));
        } else {
            throw new UsuarioNotFoundExcepcion("El alias indicado no ha sido encontrado");
        }

    }

    @PostMapping("/login")
    public EntityModel<UsuarioDto> login(@RequestBody LoginDto login) {
        System.out.println("Alias "+login.getAlias());
        System.out.println("Passw "+login.getPassword());
        Optional<UsuarioDto> usuario = usuarioService.getUsuarioByAlias(login.getAlias());
        if (usuario.isPresent()) {
            UsuarioDto usuarioItem = usuario.get();
            if (login.getPassword().equals(usuarioItem.getPassword())) {
                return EntityModel.of(usuario.get(),
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getUsuarioById(usuario.get().getId())).withSelfRel(),
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsuarios()).withRel("all-usuarios")
                );
            } else {
                throw new UsuarioNotFoundExcepcion("Credenciales incorrectas");
            }
            
        } else {
                throw new UsuarioNotFoundExcepcion("El alias indicado no ha sido encontrado");
        }

    }
    

    @PutMapping({"/{id}"})
    public EntityModel<UsuarioDto> setUsuario(@PathVariable Long id, @RequestBody UsuarioDto usuario) {
        UsuarioDto updatedUsuario = usuarioService.updateUsuario(id, usuario);
        return EntityModel.of(updatedUsuario,
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getUsuarioById(id)).withSelfRel(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsuarios()).withRel("all-usuarios"));
    }

    @DeleteMapping({"/{id}"})
    public void deleteUsuario(@PathVariable Long id) {
        usuarioService.deleteUsuario(id);
    }

}

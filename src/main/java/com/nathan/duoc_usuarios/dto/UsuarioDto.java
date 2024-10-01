package com.nathan.duoc_usuarios.dto;

import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "US_USUARIO")
public class UsuarioDto extends RepresentationModel<UsuarioDto> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rol_secuence")
    @SequenceGenerator(name = "rol_secuence", sequenceName = "seq_usuario_id", allocationSize = 1)
    @Column(name = "usuario_id")
    private Long usuarioId;
    @Column(name = "rol_id")
    private int rol;
    @Column(name = "alias")
    private String alias;
    @Column(name = "password")
    private String password;
    @Column(name = "key_word")
    private String keyWord;
    @Column(name = "nombres")
    private String nombres;
    @Column(name = "apellidos")
    private String apellidos;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "vigencia")
    private int vigencia;

    public UsuarioDto() {
    }

    public UsuarioDto(Long usuarioId, int rol, String alias, String password, String keyWord, String nombres, String apellidos, String direccion, int vigencia) {
        
        this.usuarioId = usuarioId;
        this.alias = alias;
        this.rol = rol;
        this.password = password;
        this.keyWord = keyWord;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.vigencia = 1;
    }

    public Long getId() {
        return usuarioId;
    }

    public void setId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getKey_word() {
        return keyWord;
    }

    public void setKey_word(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getVigencia() {
        return vigencia;
    }

    public void setVigencia(int vigencia) {
        this.vigencia = vigencia;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

}

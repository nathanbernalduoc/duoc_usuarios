package com.nathan.duoc_usuarios.dto;

public class UsuarioDto {
    private int usuarioId;
    private RolDto rol = new RolDto();
    private String alias;
    private String password;
    private String keyWord;
    private String nombres;
    private String apellidos;
    private String direccion;
    private int vigencia;

    public UsuarioDto() {
    }

    public UsuarioDto(int usuarioId, RolDto rol, String alias, String password, String keyWord, String nombres, String apellidos, String direccion, int vigencia) {
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

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
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

    public RolDto getRol() {
        return rol;
    }

    public void setRol(RolDto rol) {
        this.rol = rol;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

}

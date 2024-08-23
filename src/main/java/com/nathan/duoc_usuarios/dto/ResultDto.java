package com.nathan.duoc_usuarios.dto;

public class ResultDto {

    private String status;
    private String message;
    private UsuarioDto usuario;

    public ResultDto() {
    }

    public ResultDto(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public ResultDto(String status, String message, UsuarioDto usuario) {
        this.status = status;
        this.message = message;
        this.usuario = usuario;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UsuarioDto getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDto usuario) {
        this.usuario = usuario;
    }

}

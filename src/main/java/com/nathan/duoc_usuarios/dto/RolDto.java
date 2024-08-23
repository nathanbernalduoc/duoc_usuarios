package com.nathan.duoc_usuarios.dto;

public class RolDto {
    private int rolId;
    private String nombre;

    public RolDto() {
    }

    public RolDto(int rolId, String nombre) {
        this.rolId = rolId;
        this.nombre = nombre;
    }

    public int getRolId() {
        return rolId;
    }

    public void setRolId(int rolId) {
        this.rolId = rolId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
}

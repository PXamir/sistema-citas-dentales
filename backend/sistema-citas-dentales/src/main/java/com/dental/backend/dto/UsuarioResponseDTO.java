package com.dental.backend.dto;

public class UsuarioResponseDTO {

    private Integer id;
    private String nombreCompleto;
    private String correo;
    private String rol;

    public UsuarioResponseDTO(Integer id, String nombreCompleto, String correo, String rol) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.correo = correo;
        this.rol = rol;
    }

    // Getters y setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}

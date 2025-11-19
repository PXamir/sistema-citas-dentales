package com.dental.backend.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UsuarioRolId implements Serializable {

    private static final long serialVersionUID = 1L;
	
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "id_rol")
    private Integer idRol;

    public UsuarioRolId() {}

    public UsuarioRolId(Integer idUsuario, Integer idRol) {
        this.idUsuario = idUsuario;
        this.idRol = idRol;
    }

    // ======================
    // Getters & Setters
    // ======================

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    // ======================
    // equals & hashCode
    // ======================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;                         // misma referencia
        if (!(o instanceof UsuarioRolId)) return false;     // mismo tipo

        UsuarioRolId that = (UsuarioRolId) o;

        return Objects.equals(idUsuario, that.idUsuario) &&
               Objects.equals(idRol, that.idRol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsuario, idRol);
    }
}

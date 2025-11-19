package com.dental.backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import com.dental.backend.model.enums.AccionAuditoria;

@Entity
@Table(name = "auditoria")
public class Auditoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_auditoria")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @Column(name = "tabla_afectada", length = 80)
    private String tablaAfectada;

    @Enumerated(EnumType.STRING)
    private AccionAuditoria accion;

    @Column(name = "registro_id")
    private Integer registroId;

    @Column(name = "fecha_reg", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fechaRegistro;

    // ======================
    // Constructors
    // ======================
    
    // ======================
    // Getters & Setters
    // ======================
    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getTablaAfectada() {
		return tablaAfectada;
	}

	public void setTablaAfectada(String tablaAfectada) {
		this.tablaAfectada = tablaAfectada;
	}

	public AccionAuditoria getAccion() {
		return accion;
	}

	public void setAccion(AccionAuditoria accion) {
		this.accion = accion;
	}

	public Integer getRegistroId() {
		return registroId;
	}

	public void setRegistroId(Integer registroId) {
		this.registroId = registroId;
	}

	public LocalDateTime getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(LocalDateTime fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

}

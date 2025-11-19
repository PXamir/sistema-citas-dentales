package com.dental.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "consultorios")
public class Consultorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_consultorio")
    private Integer id;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(length = 100)
    private String ubicacion;

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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

}

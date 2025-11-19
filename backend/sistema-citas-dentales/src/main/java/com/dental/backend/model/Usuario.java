package com.dental.backend.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.dental.backend.model.enums.Genero;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer id;

    @Column(length = 80, nullable = false)
    private String nombre;

    @Column(length = 80)
    private String apellido;

    @Column(length = 150, nullable = false, unique = true)
    private String email;

    @Column(length = 40)
    private String telefono;

    @Enumerated(EnumType.STRING)
    private Genero genero;

    @Column(name = "fecha_nac")
    private LocalDate fechaNacimiento;

    @Column(length = 255, nullable = false)
    private String password;

    @Column(columnDefinition = "TINYINT", nullable = false)
    private Integer activo = 1;

    @Column(name = "fecha_creado", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fechaCreado;

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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getActivo() {
		return activo;
	}

	public void setActivo(Integer activo) {
		this.activo = activo;
	}

	public LocalDateTime getFechaCreado() {
		return fechaCreado;
	}

	public void setFechaCreado(LocalDateTime fechaCreado) {
		this.fechaCreado = fechaCreado;
	}
  
}

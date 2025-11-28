package com.dental.backend.dto;
import java.time.LocalDate;

public class RegistroRequest {
    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private String telefono;
    private LocalDate fechaNacimiento;
    private String genero;
    
    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }
    
    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }
}
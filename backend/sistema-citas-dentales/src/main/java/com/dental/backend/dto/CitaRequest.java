package com.dental.backend.dto;
import java.time.LocalDate;
import java.time.LocalTime;

public class CitaRequest {
    private LocalDate fecha;
    private LocalTime hora; // Asegúrate de que tu Angular mande 'hora'
    private Integer idMedico;
    private Integer idServicio;
    private Integer idUsuario;
    private String notas;

    // Genera Getters y Setters aquí...
    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public LocalTime getHora() { return hora; }
    public void setHora(LocalTime hora) { this.hora = hora; }
    public Integer getIdMedico() { return idMedico; }
    public void setIdMedico(Integer idMedico) { this.idMedico = idMedico; }
    public Integer getIdServicio() { return idServicio; }
    public void setIdServicio(Integer idServicio) { this.idServicio = idServicio; }
    public Integer getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Integer idUsuario) { this.idUsuario = idUsuario; }
    public String getNotas() { return notas; }
    public void setNotas(String notas) { this.notas = notas; }
}
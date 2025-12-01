package com.dental.backend.repository;

import com.dental.backend.model.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

public interface CitaRepository extends JpaRepository<Cita, Integer> {

    // JPQL que asume que en la entidad Cita hay una relación ManyToOne llamada "medico"
    @Query("SELECT c FROM Cita c WHERE c.medico.id = :idMedico AND c.fechCita = :fecha AND c.horaCita = :hora")
    Optional<Cita> findByMedicoAndFechaAndHora(
            @Param("idMedico") Integer idMedico,
            @Param("fecha") LocalDate fecha,
            @Param("hora") LocalTime hora
    );

    // Alternativa: método derivado (funciona si los nombres de campo siguen el patrón)
    boolean existsByMedicoIdAndFechCitaAndHoraCita(Integer medicoId, LocalDate fecha, LocalTime hora);
}

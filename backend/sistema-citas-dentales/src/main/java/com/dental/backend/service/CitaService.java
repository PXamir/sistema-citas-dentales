package com.dental.backend.service;

import com.dental.backend.exception.BookingConflictException;
import com.dental.backend.model.Cita;
import com.dental.backend.repository.CitaRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.dental.backend.model.enums.EstadoCita;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CitaService implements CitaServiceInterface {

    private final CitaRepository repo;

    public CitaService(CitaRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Cita> listar() {
        return repo.findAll();
    }

    @Override
    public Optional<Cita> listarId(Integer id) {
        return repo.findById(id);
    }

    /**
     * Guarda una cita validando que no exista conflicto (mismo médico, fecha y hora).
     * Además setea estado y fechaCreado.
     */
    @Override
    @Transactional
    public Cita save(Cita c) {
        // Validaciones básicas
        if (c.getMedico() == null || c.getMedico().getId() == null) {
            throw new IllegalArgumentException("Debe indicar el médico (id_medico).");
        }
        if (c.getFechaCita() == null || c.getHoraCita() == null) {
            throw new IllegalArgumentException("Fecha y hora de la cita son obligatorias.");
        }

        Integer idMedico = c.getMedico().getId();

        // 1) Chequear conflicto en la BD
        boolean existe = repo.existsByMedicoIdAndFechCitaAndHoraCita(idMedico, c.getFechaCita(), c.getHoraCita());
        if (existe) {
            throw new BookingConflictException("El médico ya tiene una cita en esa fecha y hora.");
        }

        // 2) Setear valores por defecto
        if (c.getEstado() == null) {
        	c.setEstado(EstadoCita.PENDIENTE);

        }
        if (c.getFechaCreado() == null) {
            c.setFechaCreado(LocalDateTime.now());
        }

        try {
            // Guardar
            return repo.save(c);
        } catch (DataIntegrityViolationException ex) {
            // Protección extra: si aún así hay conflicto por concurrencia (índice único), mapearlo a nuestra excepción
            throw new BookingConflictException("No fue posible crear la cita: horario ocupado.");
        }
    }

    @Override
    public void delete(Integer id) {
        repo.deleteById(id);
    }
}

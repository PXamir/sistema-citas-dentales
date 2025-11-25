package com.dental.backend.service;

import java.util.List;
import java.util.Optional;
import com.dental.backend.model.Cita;

public interface CitaServiceInterface {
    List<Cita> listar();
    Optional<Cita> listarId(Integer id);
    Cita getId(Integer id);
    Cita save(Cita cita);
    void delete(Integer id);
}

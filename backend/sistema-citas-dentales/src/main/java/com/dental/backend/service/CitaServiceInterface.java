package com.dental.backend.service;

import com.dental.backend.model.Cita;

import java.util.List;
import java.util.Optional;

public interface CitaServiceInterface {

    List<Cita> listar();

    Optional<Cita> listarId(Integer id);

    Cita save(Cita c);

    void delete(Integer id);
}

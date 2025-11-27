package com.dental.backend.service;

import com.dental.backend.model.Especialidad;

import java.util.List;
import java.util.Optional;

public interface EspecialidadServiceInterface {

    List<Especialidad> listar();

    Optional<Especialidad> listarId(Integer id);

    Especialidad save(Especialidad esp);

    void delete(Integer id);
}

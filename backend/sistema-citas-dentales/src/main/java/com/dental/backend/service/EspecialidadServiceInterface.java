package com.dental.backend.service;

import java.util.List;
import java.util.Optional;
import com.dental.backend.model.Especialidad;

public interface EspecialidadServiceInterface {
    List<Especialidad> listar();
    Optional<Especialidad> listarId(Integer id);
    Especialidad getId(Integer id);
    Especialidad save(Especialidad especialidad);
    void delete(Integer id);
}

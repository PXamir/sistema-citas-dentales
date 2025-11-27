package com.dental.backend.service;

import com.dental.backend.model.Prescripcion;
import java.util.List;
import java.util.Optional;

public interface PrescripcionServiceInterface {
    List<Prescripcion> listar();
    Optional<Prescripcion> listarId(Integer id);
    Prescripcion save(Prescripcion p);
    void delete(Integer id);
}

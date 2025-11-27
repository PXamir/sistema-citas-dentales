package com.dental.backend.service;

import com.dental.backend.model.HistorialMedico;
import java.util.List;
import java.util.Optional;

public interface HistorialMedicoServiceInterface {
    List<HistorialMedico> listar();
    Optional<HistorialMedico> listarId(Integer id);
    HistorialMedico save(HistorialMedico h);
    void delete(Integer id);
}

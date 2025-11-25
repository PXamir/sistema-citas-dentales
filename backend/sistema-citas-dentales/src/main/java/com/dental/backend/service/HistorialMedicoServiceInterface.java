package com.dental.backend.service;

import java.util.List;
import java.util.Optional;
import com.dental.backend.model.HistorialMedico;

public interface HistorialMedicoServiceInterface {
    List<HistorialMedico> listar();
    Optional<HistorialMedico> listarId(Integer id);
    HistorialMedico getId(Integer id);
    HistorialMedico save(HistorialMedico historial);
    void delete(Integer id);
}

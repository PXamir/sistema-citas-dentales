package com.dental.backend.service;

import java.util.List;
import java.util.Optional;
import com.dental.backend.model.Medico;

public interface MedicoServiceInterface {
    List<Medico> listar();
    Optional<Medico> listarId(Integer id);
    Medico getId(Integer id);
    Medico save(Medico medico);
    void delete(Integer id);
}

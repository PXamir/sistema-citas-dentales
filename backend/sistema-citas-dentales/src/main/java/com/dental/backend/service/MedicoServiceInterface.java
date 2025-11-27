package com.dental.backend.service;

import com.dental.backend.model.Medico;

import java.util.List;
import java.util.Optional;

public interface MedicoServiceInterface {

    List<Medico> listar();

    Optional<Medico> listarId(Integer id);

    Medico save(Medico med);

    void delete(Integer id);
}

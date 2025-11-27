package com.dental.backend.service;

import com.dental.backend.model.HorarioMedico;

import java.util.List;
import java.util.Optional;

public interface HorarioMedicoServiceInterface {

    List<HorarioMedico> listar();

    Optional<HorarioMedico> listarId(Integer id);

    HorarioMedico save(HorarioMedico h);

    void delete(Integer id);
}

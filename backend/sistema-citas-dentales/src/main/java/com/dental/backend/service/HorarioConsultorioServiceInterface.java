package com.dental.backend.service;

import com.dental.backend.model.HorarioConsultorio;

import java.util.List;
import java.util.Optional;

public interface HorarioConsultorioServiceInterface {

    List<HorarioConsultorio> listar();

    Optional<HorarioConsultorio> listarId(Integer id);

    HorarioConsultorio save(HorarioConsultorio h);

    void delete(Integer id);
}

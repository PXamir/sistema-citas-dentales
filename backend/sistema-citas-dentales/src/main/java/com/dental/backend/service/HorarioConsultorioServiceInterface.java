package com.dental.backend.service;

import java.util.List;
import java.util.Optional;
import com.dental.backend.model.HorarioConsultorio;

public interface HorarioConsultorioServiceInterface {
    List<HorarioConsultorio> listar();
    Optional<HorarioConsultorio> listarId(Integer id);
    HorarioConsultorio getId(Integer id);
    HorarioConsultorio save(HorarioConsultorio horario);
    void delete(Integer id);
}

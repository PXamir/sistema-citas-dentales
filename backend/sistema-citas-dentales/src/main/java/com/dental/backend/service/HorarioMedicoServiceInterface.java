package com.dental.backend.service;

import java.util.List;
import java.util.Optional;
import com.dental.backend.model.HorarioMedico;

public interface HorarioMedicoServiceInterface {
    List<HorarioMedico> listar();
    Optional<HorarioMedico> listarId(Integer id);
    HorarioMedico getId(Integer id);
    HorarioMedico save(HorarioMedico horario);
    void delete(Integer id);
}

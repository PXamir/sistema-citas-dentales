package com.dental.backend.service;

import java.util.List;
import java.util.Optional;
import com.dental.backend.model.Consultorio;

public interface ConsultorioServiceInterface {
    List<Consultorio> listar();
    Optional<Consultorio> listarId(Integer id);
    Consultorio getId(Integer id);
    Consultorio save(Consultorio consultorio);
    void delete(Integer id);
}

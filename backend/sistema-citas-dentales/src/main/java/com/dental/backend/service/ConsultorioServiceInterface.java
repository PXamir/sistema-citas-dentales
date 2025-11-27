package com.dental.backend.service;

import com.dental.backend.model.Consultorio;

import java.util.List;
import java.util.Optional;

public interface ConsultorioServiceInterface {

    List<Consultorio> listar();

    Optional<Consultorio> listarId(Integer id);

    Consultorio save(Consultorio c);

    void delete(Integer id);
}

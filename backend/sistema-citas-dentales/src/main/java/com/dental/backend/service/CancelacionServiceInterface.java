package com.dental.backend.service;

import com.dental.backend.model.Cancelacion;

import java.util.List;
import java.util.Optional;

public interface CancelacionServiceInterface {

    List<Cancelacion> listar();

    Optional<Cancelacion> listarId(Integer id);

    Cancelacion save(Cancelacion c);

    void delete(Integer id);
}

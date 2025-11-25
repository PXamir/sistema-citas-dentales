package com.dental.backend.service;

import java.util.List;
import java.util.Optional;
import com.dental.backend.model.Cancelacion;

public interface CancelacionServiceInterface {
    List<Cancelacion> listar();
    Optional<Cancelacion> listarId(Integer id);
    Cancelacion getId(Integer id);
    Cancelacion save(Cancelacion cancelacion);
    void delete(Integer id);
}

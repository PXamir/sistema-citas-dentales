package com.dental.backend.service;

import com.dental.backend.model.Rol;

import java.util.List;
import java.util.Optional;

public interface RolServiceInterface {

    List<Rol> listar();
    Optional<Rol> listarId(Integer id);
    Rol save(Rol r);
    void delete(Integer id);
}

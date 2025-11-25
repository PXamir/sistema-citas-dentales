package com.dental.backend.service;

import java.util.List;
import java.util.Optional;
import com.dental.backend.model.Rol;

public interface RolServiceInterface {
    List<Rol> listar();
    Optional<Rol> listarId(Integer id);
    Rol getId(Integer id);
    Rol save(Rol rol);
    void delete(Integer id);
}

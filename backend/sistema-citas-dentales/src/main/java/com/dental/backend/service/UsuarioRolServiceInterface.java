package com.dental.backend.service;

import java.util.List;
import java.util.Optional;
import com.dental.backend.model.UsuarioRol;
import com.dental.backend.model.UsuarioRolId;

public interface UsuarioRolServiceInterface {
    List<UsuarioRol> listar();
    Optional<UsuarioRol> listarId(UsuarioRolId id);
    UsuarioRol getId(UsuarioRolId id);
    UsuarioRol save(UsuarioRol usuarioRol);
    void delete(UsuarioRolId id);
}

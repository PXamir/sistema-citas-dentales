package com.dental.backend.service;

import java.util.List;
import com.dental.backend.model.UsuarioRol;
import com.dental.backend.model.UsuarioRolId;

public interface UsuarioRolServiceInterface {

    List<UsuarioRol> listar();

    UsuarioRol save(UsuarioRol ur);

    UsuarioRol getId(UsuarioRolId id);

    void delete(UsuarioRolId id);
}

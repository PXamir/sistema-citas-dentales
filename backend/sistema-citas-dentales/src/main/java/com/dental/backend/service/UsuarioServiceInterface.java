package com.dental.backend.service;

import java.util.List;
import java.util.Optional;
import com.dental.backend.model.Usuario;

public interface UsuarioServiceInterface {
    List<Usuario> listar();
    Optional<Usuario> listarId(Integer id);
    Usuario getId(Integer id);
    Usuario save(Usuario usuario);
    void delete(Integer id);
}

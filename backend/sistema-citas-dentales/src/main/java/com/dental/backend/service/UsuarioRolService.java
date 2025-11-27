package com.dental.backend.service;

import com.dental.backend.model.UsuarioRol;
import com.dental.backend.model.UsuarioRolId;
import com.dental.backend.repository.UsuarioRolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioRolService implements UsuarioRolServiceInterface {

    @Autowired
    private UsuarioRolRepository repo;

    @Override
    public List<UsuarioRol> listar() {
        return repo.findAll();
    }

    @Override
    public UsuarioRol save(UsuarioRol ur) {
        return repo.save(ur);
    }

    @Override
    public UsuarioRol getId(UsuarioRolId id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public void delete(UsuarioRolId id) {
        repo.deleteById(id);
    }
}

package com.dental.backend.service;

import com.dental.backend.model.Rol;
import com.dental.backend.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolService implements RolServiceInterface {

    @Autowired
    private RolRepository repo;

    @Override
    public List<Rol> listar() {
        return repo.findAll();
    }

    @Override
    public Optional<Rol> listarId(Integer id) {
        return repo.findById(id);
    }

    @Override
    public Rol save(Rol r) {
        return repo.save(r);
    }

    @Override
    public void delete(Integer id) {
        repo.deleteById(id);
    }
}

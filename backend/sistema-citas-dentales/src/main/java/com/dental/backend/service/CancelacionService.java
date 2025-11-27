package com.dental.backend.service;

import com.dental.backend.model.Cancelacion;
import com.dental.backend.repository.CancelacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CancelacionService implements CancelacionServiceInterface {

    @Autowired
    private CancelacionRepository repo;

    @Override
    public List<Cancelacion> listar() {
        return repo.findAll();
    }

    @Override
    public Optional<Cancelacion> listarId(Integer id) {
        return repo.findById(id);
    }

    @Override
    public Cancelacion save(Cancelacion c) {
        return repo.save(c);
    }

    @Override
    public void delete(Integer id) {
        repo.deleteById(id);
    }
}

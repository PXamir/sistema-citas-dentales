package com.dental.backend.service;

import com.dental.backend.model.Especialidad;
import com.dental.backend.repository.EspecialidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EspecialidadService implements EspecialidadServiceInterface {

    @Autowired
    private EspecialidadRepository repo;

    @Override
    public List<Especialidad> listar() {
        return repo.findAll();
    }

    @Override
    public Optional<Especialidad> listarId(Integer id) {
        return repo.findById(id);
    }

    @Override
    public Especialidad save(Especialidad esp) {
        return repo.save(esp);
    }

    @Override
    public void delete(Integer id) {
        repo.deleteById(id);
    }
}

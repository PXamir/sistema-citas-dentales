package com.dental.backend.service;

import com.dental.backend.model.Prescripcion;
import com.dental.backend.repository.PrescripcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrescripcionService implements PrescripcionServiceInterface {

    @Autowired
    private PrescripcionRepository repo;

    @Override
    public List<Prescripcion> listar() {
        return repo.findAll();
    }

    @Override
    public Optional<Prescripcion> listarId(Integer id) {
        return repo.findById(id);
    }

    @Override
    public Prescripcion save(Prescripcion p) {
        return repo.save(p);
    }

    @Override
    public void delete(Integer id) {
        repo.deleteById(id);
    }
}

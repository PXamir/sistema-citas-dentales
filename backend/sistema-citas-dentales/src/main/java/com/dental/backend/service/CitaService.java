package com.dental.backend.service;

import com.dental.backend.model.Cita;
import com.dental.backend.repository.CitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CitaService implements CitaServiceInterface {

    @Autowired
    private CitaRepository repo;

    @Override
    public List<Cita> listar() {
        return repo.findAll();
    }

    @Override
    public Optional<Cita> listarId(Integer id) {
        return repo.findById(id);
    }

    @Override
    public Cita save(Cita c) {
        return repo.save(c);
    }

    @Override
    public void delete(Integer id) {
        repo.deleteById(id);
    }
}

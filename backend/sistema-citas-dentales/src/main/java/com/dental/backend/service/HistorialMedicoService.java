package com.dental.backend.service;

import com.dental.backend.model.HistorialMedico;
import com.dental.backend.repository.HistorialMedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HistorialMedicoService implements HistorialMedicoServiceInterface {

    @Autowired
    private HistorialMedicoRepository repo;

    @Override
    public List<HistorialMedico> listar() {
        return repo.findAll();
    }

    @Override
    public Optional<HistorialMedico> listarId(Integer id) {
        return repo.findById(id);
    }

    @Override
    public HistorialMedico save(HistorialMedico h) {
        return repo.save(h);
    }

    @Override
    public void delete(Integer id) {
        repo.deleteById(id);
    }
}

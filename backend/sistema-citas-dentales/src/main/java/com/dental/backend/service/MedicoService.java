package com.dental.backend.service;

import com.dental.backend.model.Medico;
import com.dental.backend.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicoService implements MedicoServiceInterface {

    @Autowired
    private MedicoRepository repo;

    @Override
    public List<Medico> listar() {
        return repo.findAll();
    }

    @Override
    public Optional<Medico> listarId(Integer id) {
        return repo.findById(id);
    }

    @Override
    public Medico save(Medico med) {
        return repo.save(med);
    }

    @Override
    public void delete(Integer id) {
        repo.deleteById(id);
    }
}

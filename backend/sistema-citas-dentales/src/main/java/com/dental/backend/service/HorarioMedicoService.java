package com.dental.backend.service;

import com.dental.backend.model.HorarioMedico;
import com.dental.backend.repository.HorarioMedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HorarioMedicoService implements HorarioMedicoServiceInterface {

    @Autowired
    private HorarioMedicoRepository repo;

    @Override
    public List<HorarioMedico> listar() {
        return repo.findAll();
    }

    @Override
    public Optional<HorarioMedico> listarId(Integer id) {
        return repo.findById(id);
    }

    @Override
    public HorarioMedico save(HorarioMedico h) {
        return repo.save(h);
    }

    @Override
    public void delete(Integer id) {
        repo.deleteById(id);
    }
}

package com.dental.backend.service;

import com.dental.backend.model.HorarioConsultorio;
import com.dental.backend.repository.HorarioConsultorioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HorarioConsultorioService implements HorarioConsultorioServiceInterface {

    @Autowired
    private HorarioConsultorioRepository repo;

    @Override
    public List<HorarioConsultorio> listar() {
        return repo.findAll();
    }

    @Override
    public Optional<HorarioConsultorio> listarId(Integer id) {
        return repo.findById(id);
    }

    @Override
    public HorarioConsultorio save(HorarioConsultorio h) {
        return repo.save(h);
    }

    @Override
    public void delete(Integer id) {
        repo.deleteById(id);
    }
}

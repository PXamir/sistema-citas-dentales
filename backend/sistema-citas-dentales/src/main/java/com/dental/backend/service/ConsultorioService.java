package com.dental.backend.service;

import com.dental.backend.model.Consultorio;
import com.dental.backend.repository.ConsultorioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultorioService implements ConsultorioServiceInterface {

    @Autowired
    private ConsultorioRepository repo;

    @Override
    public List<Consultorio> listar() {
        return repo.findAll();
    }

    @Override
    public Optional<Consultorio> listarId(Integer id) {
        return repo.findById(id);
    }

    @Override
    public Consultorio save(Consultorio c) {
        return repo.save(c);
    }

    @Override
    public void delete(Integer id) {
        repo.deleteById(id);
    }
}

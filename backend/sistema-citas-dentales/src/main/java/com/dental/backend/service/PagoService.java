package com.dental.backend.service;

import com.dental.backend.model.Pago;
import com.dental.backend.repository.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PagoService implements PagoServiceInterface {

    @Autowired
    private PagoRepository repo;

    @Override
    public List<Pago> listar() {
        return repo.findAll();
    }

    @Override
    public Optional<Pago> listarId(Integer id) {
        return repo.findById(id);
    }

    @Override
    public Pago save(Pago p) {
        return repo.save(p);
    }

    @Override
    public void delete(Integer id) {
        repo.deleteById(id);
    }
}

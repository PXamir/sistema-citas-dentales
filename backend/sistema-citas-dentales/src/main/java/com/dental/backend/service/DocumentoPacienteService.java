package com.dental.backend.service;

import com.dental.backend.model.DocumentoPaciente;
import com.dental.backend.repository.DocumentoPacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentoPacienteService implements DocumentoPacienteServiceInterface {

    @Autowired
    private DocumentoPacienteRepository repo;

    @Override
    public List<DocumentoPaciente> listar() {
        return repo.findAll();
    }

    @Override
    public Optional<DocumentoPaciente> listarId(Integer id) {
        return repo.findById(id);
    }

    @Override
    public DocumentoPaciente save(DocumentoPaciente doc) {
        return repo.save(doc);
    }

    @Override
    public void delete(Integer id) {
        repo.deleteById(id);
    }
}

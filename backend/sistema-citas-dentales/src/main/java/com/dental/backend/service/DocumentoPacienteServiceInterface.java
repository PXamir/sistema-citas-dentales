package com.dental.backend.service;

import java.util.List;
import java.util.Optional;
import com.dental.backend.model.DocumentoPaciente;

public interface DocumentoPacienteServiceInterface {
    List<DocumentoPaciente> listar();
    Optional<DocumentoPaciente> listarId(Integer id);
    DocumentoPaciente getId(Integer id);
    DocumentoPaciente save(DocumentoPaciente documento);
    void delete(Integer id);
}

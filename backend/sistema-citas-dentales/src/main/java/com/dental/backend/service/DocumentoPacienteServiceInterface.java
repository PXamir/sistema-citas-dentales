package com.dental.backend.service;

import com.dental.backend.model.DocumentoPaciente;

import java.util.List;
import java.util.Optional;

public interface DocumentoPacienteServiceInterface {

    List<DocumentoPaciente> listar();

    Optional<DocumentoPaciente> listarId(Integer id);

    DocumentoPaciente save(DocumentoPaciente doc);

    void delete(Integer id);
}

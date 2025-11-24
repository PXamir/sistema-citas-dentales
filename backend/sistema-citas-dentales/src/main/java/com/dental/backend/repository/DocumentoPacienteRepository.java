package com.dental.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.dental.backend.model.DocumentoPaciente;

@Repository
public interface DocumentoPacienteRepository extends JpaRepository<DocumentoPaciente, Integer> {
}

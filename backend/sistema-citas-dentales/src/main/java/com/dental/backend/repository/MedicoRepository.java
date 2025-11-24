package com.dental.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.dental.backend.model.Medico;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Integer> {
}

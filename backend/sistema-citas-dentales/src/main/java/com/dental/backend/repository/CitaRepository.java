package com.dental.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.dental.backend.model.Cita;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Integer> {
}

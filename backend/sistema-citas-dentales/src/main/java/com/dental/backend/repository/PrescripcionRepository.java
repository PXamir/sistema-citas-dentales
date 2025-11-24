package com.dental.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.dental.backend.model.Prescripcion;

@Repository
public interface PrescripcionRepository extends JpaRepository<Prescripcion, Integer> {
}

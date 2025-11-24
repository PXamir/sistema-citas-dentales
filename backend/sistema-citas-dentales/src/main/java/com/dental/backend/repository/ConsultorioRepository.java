package com.dental.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.dental.backend.model.Consultorio;

@Repository
public interface ConsultorioRepository extends JpaRepository<Consultorio, Integer> {
}

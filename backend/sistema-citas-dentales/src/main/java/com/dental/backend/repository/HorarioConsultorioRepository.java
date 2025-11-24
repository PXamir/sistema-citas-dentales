package com.dental.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.dental.backend.model.HorarioConsultorio;

@Repository
public interface HorarioConsultorioRepository extends JpaRepository<HorarioConsultorio, Integer> {
}

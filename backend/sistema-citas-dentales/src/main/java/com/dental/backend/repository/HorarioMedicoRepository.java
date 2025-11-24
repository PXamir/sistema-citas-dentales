package com.dental.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.dental.backend.model.HorarioMedico;

@Repository
public interface HorarioMedicoRepository extends JpaRepository<HorarioMedico, Integer> {
}

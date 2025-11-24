package com.dental.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.dental.backend.model.Servicio;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Integer> {
}

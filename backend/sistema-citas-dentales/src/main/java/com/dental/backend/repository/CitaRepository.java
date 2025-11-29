package com.dental.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.dental.backend.model.Cita;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Integer> {
	
	List<Cita> findByUsuarioId(Integer idUsuario);
	
}

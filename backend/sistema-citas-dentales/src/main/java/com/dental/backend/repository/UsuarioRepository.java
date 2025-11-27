package com.dental.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.dental.backend.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    // Ejemplo de consulta personalizada (opcional)
	Optional<Usuario> findByEmail(String email);
    boolean existsByEmail(String email);
}

package com.dental.backend.service;

import com.dental.backend.dto.LoginRequest;
import com.dental.backend.dto.RegistroRequest;
import com.dental.backend.model.Rol;
import com.dental.backend.model.Usuario;
import com.dental.backend.model.enums.Genero;
import com.dental.backend.repository.RolRepository;
import com.dental.backend.repository.UsuarioRepository;
import com.dental.backend.repository.UsuarioRolRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(
            UsuarioRepository usuarioRepository,
            RolRepository rolRepository,
            UsuarioRolRepository usuarioRolRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // =============================
    // REGISTRO DE PACIENTE
    // =============================
    public Usuario registrarPaciente(RegistroRequest request) {

        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("El email ya está registrado");
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(request.getNombre());
        usuario.setApellido(request.getApellido());
        usuario.setEmail(request.getEmail());
        usuario.setTelefono(request.getTelefono());
        usuario.setFechaNacimiento(request.getFechaNacimiento());
        usuario.setFechaCreado(LocalDateTime.now());
        usuario.setActivo(1);
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        usuario.setGenero(Genero.valueOf(request.getGenero()));

        // 🔥 ASIGNACIÓN AUTOMÁTICA DEL ROL
        Rol rolPaciente = rolRepository.findByNombre("PACIENTE")
                .orElseThrow(() -> new RuntimeException("Rol PACIENTE no encontrado"));

        usuario.getRoles().add(rolPaciente);
        

        // 🔥 Guardar usuario (JPA guardará también usuario_rol)
        return usuarioRepository.save(usuario);
    }

    // =============================
    // LOGIN
    // =============================
    public Usuario login(LoginRequest request) {

        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(request.getPassword(), usuario.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        return usuario;
    }
}

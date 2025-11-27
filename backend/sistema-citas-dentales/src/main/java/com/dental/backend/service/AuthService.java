package com.dental.backend.service;

import com.dental.backend.dto.LoginRequest;
import com.dental.backend.dto.RegistroRequest;
import com.dental.backend.model.Rol;
import com.dental.backend.model.Usuario;
import com.dental.backend.model.enums.Genero; 
import com.dental.backend.repository.RolRepository;
import com.dental.backend.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UsuarioRepository usuarioRepository, RolRepository rolRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario registrarPaciente(RegistroRequest request) throws Exception {
        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new Exception("El email ya está registrado");
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(request.getNombre());
        usuario.setApellido(request.getApellido());
        usuario.setEmail(request.getEmail());
        usuario.setTelefono(request.getTelefono());
        usuario.setFechaNacimiento(request.getFechaNacimiento());
        usuario.setFechaCreado(LocalDateTime.now());
        usuario.setActivo(1);
        // Encriptamos la contraseña
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        
        // Asignar GENERO por defecto si no viene (o ajusta según tu Enum)
        // usuario.setGenero(Genero.OTRO); 

        // Buscar y asignar Rol PACIENTE
        Rol rolPaciente = rolRepository.findByNombre("PACIENTE")
                .orElseThrow(() -> new Exception("Error: Debes crear el rol PACIENTE en la base de datos."));
        
        Set<Rol> roles = new HashSet<>();
        roles.add(rolPaciente);
        usuario.setRoles(roles);

        return usuarioRepository.save(usuario);
    }

    public Usuario login(LoginRequest request) throws Exception {
        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new Exception("Usuario no encontrado"));

        if (!passwordEncoder.matches(request.getPassword(), usuario.getPassword())) {
            throw new Exception("Contraseña incorrecta");
        }
        return usuario;
    }
}
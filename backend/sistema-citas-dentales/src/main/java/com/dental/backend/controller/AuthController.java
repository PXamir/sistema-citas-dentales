package com.dental.backend.controller;

import com.dental.backend.dto.LoginRequest;
import com.dental.backend.dto.RegistroRequest;
import com.dental.backend.dto.UsuarioResponseDTO;
import com.dental.backend.model.Usuario;
import com.dental.backend.service.AuthService;
import com.dental.backend.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final AuthService authService;
    private final UsuarioService usuarioService;

    public AuthController(AuthService authService, UsuarioService usuarioService) {
        this.authService = authService;
        this.usuarioService = usuarioService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registrar(@RequestBody RegistroRequest request) {

        Usuario usuario = authService.registrarPaciente(request);
        UsuarioResponseDTO dto = usuarioService.convertirAResponse(usuario);

        return ResponseEntity.ok(dto);
    }


    @PostMapping("/login")
    public ResponseEntity<UsuarioResponseDTO> login(@RequestBody LoginRequest request) {
        try {
            Usuario usuario = authService.login(request);
            return ResponseEntity.ok(usuarioService.convertirAResponse(usuario));
        } catch (Exception e) {
            return ResponseEntity.status(401).build();
        }
    }
}

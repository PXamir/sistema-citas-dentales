package com.dental.backend.controller;

import com.dental.backend.dto.UsuarioResponseDTO;
import com.dental.backend.model.Usuario;
import com.dental.backend.service.UsuarioService;
import com.dental.backend.service.UsuarioServiceInterface;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioServiceInterface service;
    private final UsuarioService usuarioService; // Para convertir a DTO

    public UsuarioController(UsuarioServiceInterface service, UsuarioService usuarioService) {
        this.service = service;
        this.usuarioService = usuarioService;
    }

    // ============================
    // GET /usuarios -> Lista de usuarios DTO
    // ============================
    @GetMapping
    public List<UsuarioResponseDTO> listar() {
        return service.listar()
                .stream()
                .map(usuarioService::convertirAResponse)
                .toList();
    }

    // ============================
    // GET /usuarios/{id} -> Usuario DTO
    // ============================
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> obtener(@PathVariable Integer id) {
        return service.listarId(id)
                .map(usuario -> ResponseEntity.ok(usuarioService.convertirAResponse(usuario)))
                .orElse(ResponseEntity.notFound().build());
    }

    // ============================
    // POST /usuarios -> Crear usuario (devuelve DTO)
    // ============================
    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> crear(@Valid @RequestBody Usuario nuevo) {

        Usuario guardado = service.save(nuevo);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(guardado.getId())
                .toUri();

        UsuarioResponseDTO responseDTO = usuarioService.convertirAResponse(guardado);

        return ResponseEntity.created(location).body(responseDTO);
    }

    // ============================
    // PUT /usuarios/{id} -> Actualizar (devuelve DTO)
    // ============================
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody Usuario cambios) {

        var opt = service.listarId(id);
        if (opt.isEmpty()) return ResponseEntity.notFound().build();

        Usuario actual = opt.get();

        actual.setNombre(cambios.getNombre());
        actual.setApellido(cambios.getApellido());
        actual.setTelefono(cambios.getTelefono());
        actual.setEmail(cambios.getEmail());
        actual.setGenero(cambios.getGenero());
        actual.setFechaNacimiento(cambios.getFechaNacimiento());

        Usuario actualizado = service.save(actual);

        return ResponseEntity.ok(usuarioService.convertirAResponse(actualizado));
    }

    // ============================
    // DELETE /usuarios/{id}
    // ============================
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        if (service.listarId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

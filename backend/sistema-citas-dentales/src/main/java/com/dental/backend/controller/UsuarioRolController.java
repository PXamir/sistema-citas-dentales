package com.dental.backend.controller;

import com.dental.backend.model.UsuarioRol;
import com.dental.backend.model.UsuarioRolId;
import com.dental.backend.service.UsuarioRolServiceInterface;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/usuarios-roles")
public class UsuarioRolController {

    private final UsuarioRolServiceInterface service;

    public UsuarioRolController(UsuarioRolServiceInterface service) {
        this.service = service;
    }

    @GetMapping
    public List<UsuarioRol> listar() {
        return service.listar();
    }

    @PostMapping
    public ResponseEntity<UsuarioRol> crear(@Valid @RequestBody UsuarioRol nuevo) {
        UsuarioRol guardado = service.save(nuevo);
        return ResponseEntity.ok(guardado);
    }

    @DeleteMapping("/{idUsuario}/{idRol}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Integer idUsuario,
            @PathVariable Integer idRol) {

        UsuarioRolId id = new UsuarioRolId(idUsuario, idRol);

        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}

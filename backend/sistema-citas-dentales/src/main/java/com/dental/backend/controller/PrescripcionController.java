package com.dental.backend.controller;

import com.dental.backend.model.Prescripcion;
import com.dental.backend.service.PrescripcionServiceInterface;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/prescripciones")
public class PrescripcionController {

    private final PrescripcionServiceInterface service;

    public PrescripcionController(PrescripcionServiceInterface service) {
        this.service = service;
    }

    @GetMapping
    public List<Prescripcion> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prescripcion> obtener(@PathVariable Integer id) {
        return service.listarId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Prescripcion> crear(@Valid @RequestBody Prescripcion nueva) {
        Prescripcion guardada = service.save(nueva);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(guardada.getId())
                .toUri();

        return ResponseEntity.created(location).body(guardada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {

        if (service.listarId(id).isEmpty())
            return ResponseEntity.notFound().build();

        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

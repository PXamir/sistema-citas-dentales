package com.dental.backend.controller;

import com.dental.backend.model.DocumentoPaciente;
import com.dental.backend.service.DocumentoPacienteServiceInterface;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/documentos")
public class DocumentoPacienteController {

    private final DocumentoPacienteServiceInterface service;

    public DocumentoPacienteController(DocumentoPacienteServiceInterface service) {
        this.service = service;
    }

    @GetMapping
    public List<DocumentoPaciente> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentoPaciente> obtener(@PathVariable Integer id) {
        return service.listarId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<DocumentoPaciente> crear(@Valid @RequestBody DocumentoPaciente doc) {
        DocumentoPaciente guardado = service.save(doc);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(guardado.getId())
                .toUri();

        return ResponseEntity.created(location).body(guardado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        if (service.listarId(id).isEmpty())
            return ResponseEntity.notFound().build();

        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

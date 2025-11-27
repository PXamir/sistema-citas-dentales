package com.dental.backend.controller;

import com.dental.backend.model.Medico;
import com.dental.backend.service.MedicoServiceInterface;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/medicos")
public class MedicoController {

    private final MedicoServiceInterface service;

    public MedicoController(MedicoServiceInterface service) {
        this.service = service;
    }

    @GetMapping
    public List<Medico> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medico> obtener(@PathVariable Integer id) {
        return service.listarId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Medico> crear(@Valid @RequestBody Medico nuevo) {
        Medico guardado = service.save(nuevo);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(guardado.getId())
                .toUri();

        return ResponseEntity.created(location).body(guardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Medico> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody Medico cambios) {

        var opt = service.listarId(id);
        if (opt.isEmpty()) return ResponseEntity.notFound().build();

        Medico actual = opt.get();
        actual.setEspecialidad(cambios.getEspecialidad());
        actual.setExperienciaAnios(cambios.getExperienciaAnios());
        actual.setActivo(cambios.getActivo());

        return ResponseEntity.ok(service.save(actual));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        if (service.listarId(id).isEmpty())
            return ResponseEntity.notFound().build();

        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

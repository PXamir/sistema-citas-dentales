package com.dental.backend.controller;

import com.dental.backend.model.Especialidad;
import com.dental.backend.service.EspecialidadServiceInterface;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/especialidades")
public class EspecialidadController {

    private final EspecialidadServiceInterface service;

    public EspecialidadController(EspecialidadServiceInterface service) {
        this.service = service;
    }

    @GetMapping
    public List<Especialidad> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Especialidad> obtener(@PathVariable Integer id) {
        return service.listarId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Especialidad> crear(@Valid @RequestBody Especialidad nueva) {
        Especialidad guardada = service.save(nueva);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(guardada.getId())
                .toUri();

        return ResponseEntity.created(location).body(guardada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Especialidad> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody Especialidad cambios) {

        var opt = service.listarId(id);
        if (opt.isEmpty()) return ResponseEntity.notFound().build();

        Especialidad actual = opt.get();
        actual.setNombre(cambios.getNombre());

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

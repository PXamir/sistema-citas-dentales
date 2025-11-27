package com.dental.backend.controller;

import com.dental.backend.model.HistorialMedico;
import com.dental.backend.service.HistorialMedicoServiceInterface;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/historiales")
public class HistorialMedicoController {

    private final HistorialMedicoServiceInterface service;

    public HistorialMedicoController(HistorialMedicoServiceInterface service) {
        this.service = service;
    }

    @GetMapping
    public List<HistorialMedico> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistorialMedico> obtener(@PathVariable Integer id) {
        return service.listarId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<HistorialMedico> crear(@Valid @RequestBody HistorialMedico nuevo) {
        HistorialMedico guardado = service.save(nuevo);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(guardado.getId())
                .toUri();

        return ResponseEntity.created(location).body(guardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HistorialMedico> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody HistorialMedico cambios) {

        var opt = service.listarId(id);
        if (opt.isEmpty()) return ResponseEntity.notFound().build();

        HistorialMedico actual = opt.get();
        actual.setCita(cambios.getCita());
        actual.setDiagnostico(cambios.getDiagnostico());
        actual.setTratamiento(cambios.getTratamiento());

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

package com.dental.backend.controller;

import com.dental.backend.model.Consultorio;
import com.dental.backend.service.ConsultorioServiceInterface;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/consultorios")
public class ConsultorioController {

    private final ConsultorioServiceInterface service;

    public ConsultorioController(ConsultorioServiceInterface service) {
        this.service = service;
    }

    @GetMapping
    public List<Consultorio> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consultorio> obtener(@PathVariable Integer id) {
        return service.listarId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Consultorio> crear(@Valid @RequestBody Consultorio nuevo) {
        Consultorio guardado = service.save(nuevo);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(guardado.getId())
                .toUri();

        return ResponseEntity.created(location).body(guardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Consultorio> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody Consultorio cambios) {

        var opt = service.listarId(id);
        if (opt.isEmpty()) return ResponseEntity.notFound().build();

        Consultorio actual = opt.get();
        actual.setNombre(cambios.getNombre());
        actual.setUbicacion(cambios.getUbicacion());

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

package com.dental.backend.controller;

import com.dental.backend.model.Cancelacion;
import com.dental.backend.service.CancelacionServiceInterface;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/cancelaciones")
public class CancelacionController {

    private final CancelacionServiceInterface service;

    public CancelacionController(CancelacionServiceInterface service) {
        this.service = service;
    }

    @GetMapping
    public List<Cancelacion> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cancelacion> obtener(@PathVariable Integer id) {
        return service.listarId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Cancelacion> crear(@Valid @RequestBody Cancelacion nueva) {
        Cancelacion guardada = service.save(nueva);
        return ResponseEntity.ok(guardada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {

        if (service.listarId(id).isEmpty())
            return ResponseEntity.notFound().build();

        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

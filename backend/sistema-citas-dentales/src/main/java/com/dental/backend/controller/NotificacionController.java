package com.dental.backend.controller;

import com.dental.backend.model.Notificacion;
import com.dental.backend.service.NotificacionServiceInterface;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/notificaciones")
public class NotificacionController {

    private final NotificacionServiceInterface service;

    public NotificacionController(NotificacionServiceInterface service) {
        this.service = service;
    }

    @GetMapping
    public List<Notificacion> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notificacion> obtener(@PathVariable Integer id) {
        return service.listarId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Notificacion> crear(@Valid @RequestBody Notificacion nuevo) {
        Notificacion guardado = service.save(nuevo);
        return ResponseEntity.ok(guardado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        if (service.listarId(id).isEmpty())
            return ResponseEntity.notFound().build();

        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}

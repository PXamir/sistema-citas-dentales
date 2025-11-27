package com.dental.backend.controller;

import com.dental.backend.model.Auditoria;
import com.dental.backend.service.AuditoriaServiceInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/auditoria")
public class AuditoriaController {

    private final AuditoriaServiceInterface service;

    public AuditoriaController(AuditoriaServiceInterface service) {
        this.service = service;
    }

    @GetMapping
    public List<Auditoria> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Auditoria> obtener(@PathVariable Integer id) {
        return service.listarId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

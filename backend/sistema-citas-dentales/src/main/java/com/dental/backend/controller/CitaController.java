package com.dental.backend.controller;

import com.dental.backend.model.Cita;
import com.dental.backend.service.CitaServiceInterface;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/citas")
public class CitaController {

    private final CitaServiceInterface service;

    public CitaController(CitaServiceInterface service) {
        this.service = service;
    }

    @GetMapping
    public List<Cita> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cita> obtener(@PathVariable Integer id) {
        return service.listarId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Cita> crear(@Valid @RequestBody Cita nueva) {
        Cita guardada = service.save(nueva);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(guardada.getId())
                .toUri();

        return ResponseEntity.created(location).body(guardada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cita> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody Cita cambios) {

        var opt = service.listarId(id);
        if (opt.isEmpty()) return ResponseEntity.notFound().build();

        Cita actual = opt.get();

        actual.setUsuario(cambios.getUsuario());
        actual.setMedico(cambios.getMedico());
        actual.setServicio(cambios.getServicio());
        actual.setFechaCita(cambios.getFechaCita());
        actual.setHoraCita(cambios.getHoraCita());
        actual.setEstado(cambios.getEstado());
        actual.setNotas(cambios.getNotas());

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

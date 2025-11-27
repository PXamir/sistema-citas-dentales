package com.dental.backend.controller;

import com.dental.backend.model.HorarioMedico;
import com.dental.backend.service.HorarioMedicoServiceInterface;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/horarios-medico")
public class HorarioMedicoController {

    private final HorarioMedicoServiceInterface service;

    public HorarioMedicoController(HorarioMedicoServiceInterface service) {
        this.service = service;
    }

    @GetMapping
    public List<HorarioMedico> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<HorarioMedico> obtener(@PathVariable Integer id) {
        return service.listarId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<HorarioMedico> crear(@Valid @RequestBody HorarioMedico nuevo) {
        HorarioMedico guardado = service.save(nuevo);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(guardado.getId())
                .toUri();

        return ResponseEntity.created(location).body(guardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HorarioMedico> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody HorarioMedico cambios) {

        var opt = service.listarId(id);
        if (opt.isEmpty()) return ResponseEntity.notFound().build();

        HorarioMedico actual = opt.get();

        actual.setDia(cambios.getDia());
        actual.setHoraInicio(cambios.getHoraInicio());
        actual.setHoraFin(cambios.getHoraFin());
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

package com.dental.backend.controller;

import com.dental.backend.model.HorarioConsultorio;
import com.dental.backend.service.HorarioConsultorioServiceInterface;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/horarios-consultorio")
public class HorarioConsultorioController {

    private final HorarioConsultorioServiceInterface service;

    public HorarioConsultorioController(HorarioConsultorioServiceInterface service) {
        this.service = service;
    }

    @GetMapping
    public List<HorarioConsultorio> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<HorarioConsultorio> obtener(@PathVariable Integer id) {
        return service.listarId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<HorarioConsultorio> crear(@Valid @RequestBody HorarioConsultorio nuevo) {
        HorarioConsultorio guardado = service.save(nuevo);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(guardado.getId())
                .toUri();

        return ResponseEntity.created(location).body(guardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HorarioConsultorio> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody HorarioConsultorio cambios) {

        var opt = service.listarId(id);
        if (opt.isEmpty()) return ResponseEntity.notFound().build();

        HorarioConsultorio actual = opt.get();

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

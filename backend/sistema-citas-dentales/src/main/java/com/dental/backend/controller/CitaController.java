package com.dental.backend.controller;

import com.dental.backend.exception.BookingConflictException;
import com.dental.backend.model.Cita;
import com.dental.backend.service.CitaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/citas")
public class CitaController {

    private final CitaService citaService;

    public CitaController(CitaService citaService) {
        this.citaService = citaService;
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearCita(@RequestBody Cita cita) {
        try {
            Cita nueva = citaService.save(cita);
            return ResponseEntity.ok(nueva);
        } catch (BookingConflictException bce) {
            return ResponseEntity.badRequest().body(bce.getMessage());
        } catch (IllegalArgumentException iae) {
            return ResponseEntity.badRequest().body(iae.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error interno al crear la cita");
        }
    }
}

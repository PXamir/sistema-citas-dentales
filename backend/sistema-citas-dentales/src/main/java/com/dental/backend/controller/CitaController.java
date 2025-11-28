package com.dental.backend.controller;

import com.dental.backend.dto.CitaRequest; // Importa el DTO
import com.dental.backend.model.*;
import com.dental.backend.model.enums.EstadoCita; // Importa tu Enum
import com.dental.backend.repository.*; // Importa los repositorios
import com.dental.backend.service.CitaServiceInterface;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/citas")
public class CitaController {

    private final CitaServiceInterface service;
    
    // Agregamos los repositorios necesarios para buscar por ID
    private final UsuarioRepository usuarioRepo;
    private final MedicoRepository medicoRepo;
    private final ServicioRepository servicioRepo;

    // Actualizamos el constructor
    public CitaController(CitaServiceInterface service, 
                          UsuarioRepository usuarioRepo,
                          MedicoRepository medicoRepo,
                          ServicioRepository servicioRepo) {
        this.service = service;
        this.usuarioRepo = usuarioRepo;
        this.medicoRepo = medicoRepo;
        this.servicioRepo = servicioRepo;
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

    // --- AQUÍ ESTÁ EL CAMBIO IMPORTANTE ---
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody CitaRequest request) { // Usamos CitaRequest en vez de Cita
        try {
            // 1. Buscamos las entidades reales usando los IDs del JSON
            Usuario usuario = usuarioRepo.findById(request.getIdUsuario())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            
            Medico medico = medicoRepo.findById(request.getIdMedico())
                    .orElseThrow(() -> new RuntimeException("Médico no encontrado"));
            
            Servicio servicio = servicioRepo.findById(request.getIdServicio())
                    .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));

            // 2. Construimos la Cita manualmente
            Cita nuevaCita = new Cita();
            nuevaCita.setFechaCita(request.getFecha());
            nuevaCita.setHoraCita(request.getHora());
            nuevaCita.setNotas(request.getNotas());
            nuevaCita.setEstado(EstadoCita.PENDIENTE); // Estado inicial
            nuevaCita.setFechaCreado(LocalDateTime.now());
            
            // 3. Conectamos las relaciones
            nuevaCita.setUsuario(usuario);
            nuevaCita.setMedico(medico);
            nuevaCita.setServicio(servicio);

            // 4. Guardamos usando tu servicio existente
            Cita guardada = service.save(nuevaCita);

            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(guardada.getId())
                    .toUri();

            return ResponseEntity.created(location).body(guardada);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al agendar: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cita> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody Cita cambios) {

        var opt = service.listarId(id);
        if (opt.isEmpty()) return ResponseEntity.notFound().build();

        Cita actual = opt.get();
        // Nota: Para actualizar relaciones complejas también necesitarías lógica similar al POST
        // pero por ahora actualizamos datos básicos
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
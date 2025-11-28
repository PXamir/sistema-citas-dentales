package com.dental.backend.controller;

import com.dental.backend.model.Servicio;
import com.dental.backend.repository.ServicioRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/servicios")
public class ServicioController {

    private final ServicioRepository servicioRepository;

    public ServicioController(ServicioRepository servicioRepository) {
        this.servicioRepository = servicioRepository;
    }

    // Endpoint: GET http://localhost:8085/servicios
    @GetMapping
    public List<Servicio> listarServicios() {
        return servicioRepository.findAll();
    }
}
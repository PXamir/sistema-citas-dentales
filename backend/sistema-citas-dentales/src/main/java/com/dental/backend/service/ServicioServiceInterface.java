package com.dental.backend.service;

import java.util.List;
import java.util.Optional;
import com.dental.backend.model.Servicio;

public interface ServicioServiceInterface {
    List<Servicio> listar();
    Optional<Servicio> listarId(Integer id);
    Servicio getId(Integer id);
    Servicio save(Servicio servicio);
    void delete(Integer id);
}

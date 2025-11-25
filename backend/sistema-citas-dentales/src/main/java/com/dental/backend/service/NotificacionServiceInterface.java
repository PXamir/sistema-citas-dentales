package com.dental.backend.service;

import java.util.List;
import java.util.Optional;
import com.dental.backend.model.Notificacion;

public interface NotificacionServiceInterface {
    List<Notificacion> listar();
    Optional<Notificacion> listarId(Integer id);
    Notificacion getId(Integer id);
    Notificacion save(Notificacion notificacion);
    void delete(Integer id);
}

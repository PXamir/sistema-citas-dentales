package com.dental.backend.service;

import com.dental.backend.model.Notificacion;
import java.util.List;
import java.util.Optional;

public interface NotificacionServiceInterface {
    List<Notificacion> listar();
    Optional<Notificacion> listarId(Integer id);
    Notificacion save(Notificacion n);
    void delete(Integer id);
}

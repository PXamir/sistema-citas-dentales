package com.dental.backend.service;

import com.dental.backend.model.Notificacion;
import com.dental.backend.repository.NotificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificacionService implements NotificacionServiceInterface {

    @Autowired
    private NotificacionRepository repo;

    @Override
    public List<Notificacion> listar() {
        return repo.findAll();
    }

    @Override
    public Optional<Notificacion> listarId(Integer id) {
        return repo.findById(id);
    }

    @Override
    public Notificacion save(Notificacion n) {
        return repo.save(n);
    }

    @Override
    public void delete(Integer id) {
        repo.deleteById(id);
    }
}

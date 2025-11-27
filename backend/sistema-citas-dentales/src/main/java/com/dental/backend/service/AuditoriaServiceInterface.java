package com.dental.backend.service;

import com.dental.backend.model.Auditoria;

import java.util.List;
import java.util.Optional;

public interface AuditoriaServiceInterface {

    List<Auditoria> listar();

    Optional<Auditoria> listarId(Integer id);

    Auditoria save(Auditoria aud);

    void delete(Integer id);
}

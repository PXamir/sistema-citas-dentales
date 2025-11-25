package com.dental.backend.service;

import java.util.List;
import java.util.Optional;
import com.dental.backend.model.Auditoria;

public interface AuditoriaServiceInterface {
    List<Auditoria> listar();
    Optional<Auditoria> listarId(Integer id);
    Auditoria getId(Integer id);
    int save(int auditoria);
    void delete(Integer id);
}

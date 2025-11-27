package com.dental.backend.service;

import com.dental.backend.model.Pago;
import java.util.List;
import java.util.Optional;

public interface PagoServiceInterface {
    List<Pago> listar();
    Optional<Pago> listarId(Integer id);
    Pago save(Pago p);
    void delete(Integer id);
}

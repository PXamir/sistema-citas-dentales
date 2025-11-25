package com.dental.backend.service;

import java.util.List;
import java.util.Optional;
import com.dental.backend.model.Pago;

public interface PagoServiceInterface {
    List<Pago> listar();
    Optional<Pago> listarId(Integer id);
    Pago getId(Integer id);
    Pago save(Pago pago);
    void delete(Integer id);
}

package com.dental.backend.service;

import java.util.List;
import java.util.Optional;
import com.dental.backend.model.Prescripcion;

public interface PrescripcionServiceInterface {
	List<Prescripcion> listar();
    Optional<Prescripcion> listarId(Integer id);
    Prescripcion getId(Integer id);
    Prescripcion save(Prescripcion prescripcion);
}

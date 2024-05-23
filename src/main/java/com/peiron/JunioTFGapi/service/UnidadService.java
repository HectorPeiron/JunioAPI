package com.peiron.JunioTFGapi.service;

import com.peiron.JunioTFGapi.domain.Animal;
import com.peiron.JunioTFGapi.domain.Unidad;
import com.peiron.JunioTFGapi.exception.UnidadNotFoundException;

import java.util.List;

public interface UnidadService {
    Unidad findById(long id) throws UnidadNotFoundException;

    Unidad addUnidad(Unidad unidad);

    List<Unidad> findAll();

}

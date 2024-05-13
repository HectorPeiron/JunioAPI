package com.peiron.JunioTFGapi.service;


import com.peiron.JunioTFGapi.domain.Unidad;
import com.peiron.JunioTFGapi.exception.UnidadNotFoundException;

public interface UnidadService {
    Unidad findById(long id) throws UnidadNotFoundException;

}

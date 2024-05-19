package com.peiron.JunioTFGapi.service;


import com.peiron.JunioTFGapi.domain.TipoRecurso;
import com.peiron.JunioTFGapi.exception.TipoRecursoNotFoundException;

public interface TipoRecursoService {
    TipoRecurso findById(long id) throws TipoRecursoNotFoundException;

    TipoRecurso addTipoRecurso(TipoRecurso tipoRecurso);
}

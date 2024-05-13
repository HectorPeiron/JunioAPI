package com.peiron.JunioTFGapi.service;



import com.peiron.JunioTFGapi.domain.Recurso;
import com.peiron.JunioTFGapi.exception.RecursoNotFoundException;
import com.peiron.JunioTFGapi.exception.TipoAnimalNotFoundException;
import com.peiron.JunioTFGapi.exception.TipoRecursoNotFoundException;
import com.peiron.JunioTFGapi.exception.UnidadNotFoundException;

import java.util.List;

public interface RecursoService {

    List<Recurso> findAll();

    Recurso addRecurso(Recurso recurso ,long tipoRecursoId, long tipoAnimalId, long unidadId ) throws TipoAnimalNotFoundException, TipoRecursoNotFoundException, UnidadNotFoundException;

    void deleteRecurso(long id) throws RecursoNotFoundException;

    Recurso modifyRecurso(long id, long tipoRecursoId, long tipoAnimalId, long unidadId,  Recurso recurso) throws RecursoNotFoundException, TipoAnimalNotFoundException, TipoRecursoNotFoundException, UnidadNotFoundException;

    Recurso findById(long id) throws RecursoNotFoundException;
}

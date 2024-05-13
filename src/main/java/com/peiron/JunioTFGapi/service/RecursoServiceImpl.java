package com.peiron.JunioTFGapi.service;

import com.peiron.JunioTFGapi.domain.*;
import com.peiron.JunioTFGapi.exception.*;
import com.peiron.JunioTFGapi.repository.RecursoRepository;
import com.peiron.JunioTFGapi.repository.TipoAnimalRepository;
import com.peiron.JunioTFGapi.repository.TipoRecursoRepository;
import com.peiron.JunioTFGapi.repository.UnidadRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecursoServiceImpl implements RecursoService{

    @Autowired
    RecursoRepository recursoRepository;

    @Autowired
    TipoRecursoRepository tipoRecursoRepository;

    @Autowired
    TipoAnimalRepository tipoAnimalRepository;

    @Autowired
    UnidadRepository unidadRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Recurso addRecurso(Recurso recurso, long tipoRecursoId, long tipoAnimalId, long unidadId)
            throws  TipoAnimalNotFoundException, TipoRecursoNotFoundException, UnidadNotFoundException {
        TipoRecurso tipoRecurso = tipoRecursoRepository.findById(tipoRecursoId)
                .orElseThrow(TipoRecursoNotFoundException::new);
        recurso.setRecursoTipoRecurso(tipoRecurso);

        TipoAnimal tipoAnimal = tipoAnimalRepository.findById(tipoAnimalId)
                .orElseThrow(TipoAnimalNotFoundException::new);
        recurso.setRecursoTipoAnimal(tipoAnimal);

        Unidad unidad = unidadRepository.findById(unidadId)
                .orElseThrow(UnidadNotFoundException::new);
        recurso.setRecursoUnidad(unidad);

        return recursoRepository.save(recurso);
    }


    @Override
    public Recurso modifyRecurso(long id, long tipoRecursoId, long tipoAnimalId, long unidadId, Recurso newRecurso)
            throws RecursoNotFoundException,  TipoAnimalNotFoundException, TipoRecursoNotFoundException, UnidadNotFoundException {

        TipoAnimal existingTipoAnimal = tipoAnimalRepository.findById(tipoAnimalId)
                .orElseThrow(TipoAnimalNotFoundException::new);
        newRecurso.setRecursoTipoAnimal(existingTipoAnimal);

        TipoRecurso existingTipoRecurso = tipoRecursoRepository.findById(tipoRecursoId)
                .orElseThrow(TipoRecursoNotFoundException::new);
        newRecurso.setRecursoTipoRecurso(existingTipoRecurso);

        Unidad existingUnidad = unidadRepository.findById(unidadId)
                .orElseThrow(UnidadNotFoundException::new);
        newRecurso.setRecursoUnidad(existingUnidad);

        Recurso existingRecurso = recursoRepository.findById(id)
                .orElseThrow(RecursoNotFoundException::new);
        newRecurso.setId(id);
        modelMapper.map(newRecurso, existingRecurso);

        return recursoRepository.save(existingRecurso);
    }


    @Override
    public List<Recurso> findAll() {
        return recursoRepository.findAll();
    }


    @Override
    public Recurso findById(long id) throws RecursoNotFoundException {
        return recursoRepository.findById(id)
                .orElseThrow(RecursoNotFoundException::new);
    }

    @Override
    public void deleteRecurso(long id) throws RecursoNotFoundException {
        Recurso recurso = recursoRepository.findById(id)
                .orElseThrow(RecursoNotFoundException::new);
        recursoRepository.delete(recurso);
    }



}

package com.peiron.JunioTFGapi.service;

import com.peiron.JunioTFGapi.domain.Animal;
import com.peiron.JunioTFGapi.domain.TipoAnimal;
import com.peiron.JunioTFGapi.exception.TipoAnimalNotFoundException;

import java.util.List;

public interface TipoAnimalService {
    TipoAnimal findById(long id) throws TipoAnimalNotFoundException;

    TipoAnimal addTipoAnimal (TipoAnimal tipoAnimal);

    List<TipoAnimal> findAll();

}

package com.peiron.JunioTFGapi.service;

import com.peiron.JunioTFGapi.domain.Animal;
import com.peiron.JunioTFGapi.domain.TipoAnimal;
import com.peiron.JunioTFGapi.exception.TipoAnimalNotFoundException;

public interface TipoAnimalService {
    TipoAnimal findById(long id) throws TipoAnimalNotFoundException;

}

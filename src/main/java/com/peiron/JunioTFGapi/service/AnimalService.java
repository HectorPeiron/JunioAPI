package com.peiron.JunioTFGapi.service;

import com.peiron.JunioTFGapi.domain.Animal;
import com.peiron.JunioTFGapi.exception.AnimalNotFoundException;
import com.peiron.JunioTFGapi.exception.CrianzaNotFoundException;
import com.peiron.JunioTFGapi.exception.TipoAnimalNotFoundException;

import java.util.List;

public interface AnimalService {


    List<Animal> findAll();

    Animal addAnimal(Animal animal, long crianzaId ,long tipoAnimalId) throws CrianzaNotFoundException, TipoAnimalNotFoundException;

    void deleteAnimal(long id) throws AnimalNotFoundException;

    Animal modifyAnimal(long id, long crianzaId, long tipoAnimalId, Animal animal) throws AnimalNotFoundException, CrianzaNotFoundException, TipoAnimalNotFoundException;

    Animal findById(long id) throws AnimalNotFoundException;
}

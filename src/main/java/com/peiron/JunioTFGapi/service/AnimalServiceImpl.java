package com.peiron.JunioTFGapi.service;

import com.peiron.JunioTFGapi.domain.Animal;
import com.peiron.JunioTFGapi.domain.Crianza;
import com.peiron.JunioTFGapi.domain.TipoAnimal;
import com.peiron.JunioTFGapi.exception.AnimalNotFoundException;
import com.peiron.JunioTFGapi.exception.CrianzaNotFoundException;
import com.peiron.JunioTFGapi.exception.TipoAnimalNotFoundException;
import com.peiron.JunioTFGapi.repository.AnimalRepository;
import com.peiron.JunioTFGapi.repository.CrianzaRepository;
import com.peiron.JunioTFGapi.repository.TipoAnimalRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalServiceImpl implements AnimalService {
    @Autowired
    AnimalRepository animalRepository;

    @Autowired
    CrianzaRepository crianzaRepository;

    @Autowired
    TipoAnimalRepository tipoAnimalRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Animal addAnimal(Animal animal, long crianzaId, long tipoAnimalId)
            throws CrianzaNotFoundException, TipoAnimalNotFoundException {
        Crianza crianza = crianzaRepository.findById(crianzaId)
                    .orElseThrow(CrianzaNotFoundException::new);
        animal.setAnimalCrianza(crianza);

        TipoAnimal tipoAnimal = tipoAnimalRepository.findById(tipoAnimalId)
                .orElseThrow(TipoAnimalNotFoundException::new);
        animal.setAnimalTipoAnimal(tipoAnimal);

        return animalRepository.save(animal);
    }


    @Override
    public List<Animal> findAll() {
        return animalRepository.findAll();
    }

    @Override
    public Animal findById(long id) throws AnimalNotFoundException {
        return animalRepository.findById(id).orElseThrow(AnimalNotFoundException::new);
    }

    @Override
    public void deleteAnimal(long id) throws AnimalNotFoundException {
        Animal animal = animalRepository.findById(id).orElseThrow(AnimalNotFoundException::new);
        animalRepository.delete(animal);
    }

    @Override
    public Animal modifyAnimal(long id, long crianzaId, long tipoAnimalId, Animal newAnimal) throws AnimalNotFoundException,  CrianzaNotFoundException, TipoAnimalNotFoundException {

        Crianza existingCrianza = crianzaRepository.findById(crianzaId)
                .orElseThrow(CrianzaNotFoundException::new);
        newAnimal.setAnimalCrianza(existingCrianza);

        TipoAnimal existingTipoAnimal = tipoAnimalRepository.findById(tipoAnimalId)
                .orElseThrow(TipoAnimalNotFoundException::new);
        newAnimal.setAnimalTipoAnimal(existingTipoAnimal);

        Animal existingAnimal = animalRepository.findById(id)
                .orElseThrow(AnimalNotFoundException::new);
        newAnimal.setId(id);
        modelMapper.map(newAnimal, existingAnimal);

        return animalRepository.save(existingAnimal);
    }

}

package com.peiron.JunioTFGapi.service;

import com.peiron.JunioTFGapi.domain.TipoAnimal;
import com.peiron.JunioTFGapi.exception.TipoAnimalNotFoundException;
import com.peiron.JunioTFGapi.repository.TipoAnimalRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoAnimalServiceImpl implements TipoAnimalService {

    @Autowired
    TipoAnimalRepository tipoAnimalRepository;

    @Autowired
    private ModelMapper modelMapper;



    @Override
    public TipoAnimal findById(long id) throws TipoAnimalNotFoundException {
        return tipoAnimalRepository.findById(id).orElseThrow(TipoAnimalNotFoundException::new);
    }


}

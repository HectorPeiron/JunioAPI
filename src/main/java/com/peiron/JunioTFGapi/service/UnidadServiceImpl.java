package com.peiron.JunioTFGapi.service;

import com.peiron.JunioTFGapi.domain.Animal;
import com.peiron.JunioTFGapi.domain.Crianza;
import com.peiron.JunioTFGapi.domain.TipoAnimal;
import com.peiron.JunioTFGapi.domain.Unidad;
import com.peiron.JunioTFGapi.exception.CrianzaNotFoundException;
import com.peiron.JunioTFGapi.exception.TipoAnimalNotFoundException;
import com.peiron.JunioTFGapi.exception.UnidadNotFoundException;
import com.peiron.JunioTFGapi.repository.UnidadRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnidadServiceImpl implements UnidadService {

    @Autowired
    UnidadRepository unidadRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Unidad addUnidad(Unidad unidad) {
        return unidadRepository.save(unidad);
    }

    @Override
    public Unidad findById(long id) throws UnidadNotFoundException {
        return unidadRepository.findById(id)
                .orElseThrow(UnidadNotFoundException::new);
    }

}

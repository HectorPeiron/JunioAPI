package com.peiron.JunioTFGapi.service;

import com.peiron.JunioTFGapi.domain.Unidad;
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
    public Unidad findById(long id) throws UnidadNotFoundException {
        return unidadRepository.findById(id)
                .orElseThrow(UnidadNotFoundException::new);
    }

}

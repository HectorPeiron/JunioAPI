package com.peiron.JunioTFGapi.service;


import com.peiron.JunioTFGapi.domain.TipoRecurso;
import com.peiron.JunioTFGapi.exception.TipoRecursoNotFoundException;
import com.peiron.JunioTFGapi.repository.TipoRecursoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoRecursoServiceImpl implements TipoRecursoService {

    @Autowired
    TipoRecursoRepository tipoRecursoRepository;

    @Autowired
    private ModelMapper modelMapper;



    @Override
    public TipoRecurso findById(long id) throws TipoRecursoNotFoundException {
        return tipoRecursoRepository.findById(id)
                .orElseThrow(TipoRecursoNotFoundException::new);
    }

}

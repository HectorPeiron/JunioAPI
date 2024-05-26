package com.peiron.JunioTFGapi.service;


import com.peiron.JunioTFGapi.domain.TipoBaja;
import com.peiron.JunioTFGapi.exception.TipoBajaNotFoundException;
import com.peiron.JunioTFGapi.repository.TipoBajaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoBajaServiceImpl implements TipoBajaService {

    @Autowired
    TipoBajaRepository tipoBajaRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public TipoBaja addTipoBaja(TipoBaja tipoBaja) {
        return tipoBajaRepository.save(tipoBaja);
    }

    @Override
    public TipoBaja findById(long id) throws TipoBajaNotFoundException {
        return tipoBajaRepository.findById(id)
                .orElseThrow(TipoBajaNotFoundException::new);
    }
    @Override
    public List<TipoBaja> findAll() {
        return tipoBajaRepository.findAll();
    }

}

package com.peiron.JunioTFGapi.service;

import com.peiron.JunioTFGapi.domain.Baja;
import com.peiron.JunioTFGapi.domain.Crianza;
import com.peiron.JunioTFGapi.domain.TipoBaja;
import com.peiron.JunioTFGapi.exception.BajaNotFoundException;
import com.peiron.JunioTFGapi.exception.CrianzaNotFoundException;
import com.peiron.JunioTFGapi.exception.TipoBajaNotFoundException;
import com.peiron.JunioTFGapi.repository.BajaRepository;
import com.peiron.JunioTFGapi.repository.CrianzaRepository;

import com.peiron.JunioTFGapi.repository.TipoBajaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BajaServiceImpl implements BajaService {
    @Autowired
    BajaRepository bajaRepository;

    @Autowired
    CrianzaRepository crianzaRepository;

    @Autowired
    TipoBajaRepository tipoBajaRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override

    public Baja addBaja(Baja baja, long tipoBajaId, long crianzaId)
            throws CrianzaNotFoundException, TipoBajaNotFoundException {
        TipoBaja tipoBaja = tipoBajaRepository.findById(tipoBajaId)
                .orElseThrow(() -> new TipoBajaNotFoundException("TipoBaja not found"));
        Crianza crianza = crianzaRepository.findById(crianzaId)
                .orElseThrow(() -> new CrianzaNotFoundException("Crianza not found"));

        baja.setBajaTipoBaja(tipoBaja);
        baja.setBajaCrianza(crianza);

        return bajaRepository.save(baja);
    }

    @Override
    public Baja modifyBaja(long id, long tipoBajaId, long crianzaId, Baja newBajaDetails)
            throws BajaNotFoundException, CrianzaNotFoundException, TipoBajaNotFoundException {
        Baja existingBaja = bajaRepository.findById(id)
                .orElseThrow(BajaNotFoundException::new);
        modelMapper.map(newBajaDetails, existingBaja);

        existingBaja.setBajaCrianza(crianzaRepository.findById(crianzaId)
                .orElseThrow(CrianzaNotFoundException::new));
        existingBaja.setBajaTipoBaja(tipoBajaRepository.findById(tipoBajaId)
                .orElseThrow(TipoBajaNotFoundException::new));

        return bajaRepository.save(existingBaja);
    }


    @Override
    public List<Baja> findAll() {
        return bajaRepository.findAll();
    }

    @Override
    public Baja findById(long id) throws BajaNotFoundException {
        return bajaRepository.findById(id).orElseThrow(BajaNotFoundException::new);
    }

    @Override
    public void deleteBaja(long id) throws BajaNotFoundException {
        Baja baja = bajaRepository.findById(id).orElseThrow(BajaNotFoundException::new);
        bajaRepository.delete(baja);
    }




}

package com.peiron.JunioTFGapi.service;

import com.peiron.JunioTFGapi.domain.Baja;
import com.peiron.JunioTFGapi.exception.BajaNotFoundException;
import com.peiron.JunioTFGapi.exception.CrianzaNotFoundException;
import com.peiron.JunioTFGapi.exception.TipoBajaNotFoundException;

import java.util.List;

public interface BajaService {


    List<Baja> findAll();

    Baja addBaja(Baja baja, long crianzaId ,long tipoBajaId) throws CrianzaNotFoundException, TipoBajaNotFoundException;

    void deleteBaja(long id) throws BajaNotFoundException;

    Baja modifyBaja(long id, long crianzaId, long tipoBajaId, Baja baja) throws BajaNotFoundException, CrianzaNotFoundException, TipoBajaNotFoundException;

    Baja findById(long id) throws BajaNotFoundException;

    List<Baja> findBajasByCrianzaId(Long crianzaId) throws CrianzaNotFoundException;
}

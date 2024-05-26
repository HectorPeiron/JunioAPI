package com.peiron.JunioTFGapi.service;

import com.peiron.JunioTFGapi.domain.TipoBaja;
import com.peiron.JunioTFGapi.exception.TipoBajaNotFoundException;

import java.util.List;

public interface TipoBajaService {
    TipoBaja findById(long id) throws TipoBajaNotFoundException;

    TipoBaja addTipoBaja (TipoBaja tipoBaja);

    List<TipoBaja> findAll();

}

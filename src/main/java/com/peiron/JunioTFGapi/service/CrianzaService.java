package com.peiron.JunioTFGapi.service;

import com.peiron.JunioTFGapi.domain.Crianza;
import com.peiron.JunioTFGapi.exception.AnimalNotFoundException;
import com.peiron.JunioTFGapi.exception.CompraNotFoundException;
import com.peiron.JunioTFGapi.exception.CrianzaNotFoundException;
import com.peiron.JunioTFGapi.exception.VeterinarioNotFoundException;

import java.util.List;

public interface CrianzaService {


    List<Crianza> findAll();

    Crianza addCrianza(Crianza crianza, long animalId, long compraId, long veterinarioId)
            throws AnimalNotFoundException, CompraNotFoundException, VeterinarioNotFoundException;

    void deleteCrianza(long id) throws CrianzaNotFoundException;

    Crianza modifyCrianza(long id, long animalId, long compraId, long veterinarioId, Crianza crianza)
            throws CrianzaNotFoundException, AnimalNotFoundException, CompraNotFoundException, VeterinarioNotFoundException;

    Crianza findById(long id) throws CrianzaNotFoundException;
}

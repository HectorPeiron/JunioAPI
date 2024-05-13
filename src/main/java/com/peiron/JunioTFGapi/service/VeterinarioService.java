package com.peiron.JunioTFGapi.service;

import com.peiron.JunioTFGapi.domain.Veterinario;
import com.peiron.JunioTFGapi.exception.CrianzaNotFoundException;
import com.peiron.JunioTFGapi.exception.VeterinarioNotFoundException;

import java.util.List;

public interface VeterinarioService {

    Veterinario addVeterinario(Veterinario veterinario, long crianzaId) throws CrianzaNotFoundException;

    List<Veterinario> findAll();

    void deleteVeterinario(long id) throws VeterinarioNotFoundException;

    Veterinario findById(long id) throws VeterinarioNotFoundException;

    Veterinario modifyVeterinario(long id, long crianzaId, Veterinario veterinario) throws VeterinarioNotFoundException, CrianzaNotFoundException;

}





package com.peiron.JunioTFGapi.service;


import com.peiron.JunioTFGapi.domain.Crianza;
import com.peiron.JunioTFGapi.domain.Veterinario;
import com.peiron.JunioTFGapi.exception.CrianzaNotFoundException;
import com.peiron.JunioTFGapi.exception.VeterinarioNotFoundException;
import com.peiron.JunioTFGapi.repository.CrianzaRepository;
import com.peiron.JunioTFGapi.repository.VeterinarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
    public class VeterinarioServiceImpl implements VeterinarioService {

    @Autowired
    VeterinarioRepository veterinarioRepository;

    @Autowired
    CrianzaRepository crianzaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Veterinario addVeterinario(Veterinario veterinario, long crianzaId)
            throws CrianzaNotFoundException {
        Crianza crianza = crianzaRepository.findById(crianzaId)
                .orElseThrow(CrianzaNotFoundException::new);
        veterinario.setVeterinarioCrianza(crianza);

        return veterinarioRepository.save(veterinario);
    }

    @Override
    public List<Veterinario> findAll() {
        return veterinarioRepository.findAll();
    }



    @Override
    public Veterinario findById(long id) throws VeterinarioNotFoundException {
        return veterinarioRepository.findById(id)
                .orElseThrow(VeterinarioNotFoundException::new);
    }

    @Override
    public void deleteVeterinario(long id) throws VeterinarioNotFoundException {
        Veterinario veterinario = veterinarioRepository.findById(id)
                .orElseThrow(VeterinarioNotFoundException::new);
        veterinarioRepository.delete(veterinario);
    }

    @Override
    public Veterinario modifyVeterinario(long id, long crianzaId, Veterinario newVeterinario) throws VeterinarioNotFoundException,  CrianzaNotFoundException {

        Crianza existingCrianza = crianzaRepository.findById(crianzaId)
                .orElseThrow(CrianzaNotFoundException::new);
        newVeterinario.setVeterinarioCrianza(existingCrianza);


        Veterinario existingVeterinario = veterinarioRepository.findById(id)
                .orElseThrow(VeterinarioNotFoundException::new);
        newVeterinario.setId(id);
        modelMapper.map(newVeterinario, existingVeterinario);

        return veterinarioRepository.save(existingVeterinario);
    }

}

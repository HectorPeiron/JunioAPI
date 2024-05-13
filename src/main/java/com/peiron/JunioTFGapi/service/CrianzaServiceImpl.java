package com.peiron.JunioTFGapi.service;


import com.peiron.JunioTFGapi.domain.Animal;
import com.peiron.JunioTFGapi.domain.Compra;
import com.peiron.JunioTFGapi.domain.Crianza;
import com.peiron.JunioTFGapi.domain.Veterinario;
import com.peiron.JunioTFGapi.exception.AnimalNotFoundException;
import com.peiron.JunioTFGapi.exception.CompraNotFoundException;
import com.peiron.JunioTFGapi.exception.CrianzaNotFoundException;
import com.peiron.JunioTFGapi.exception.VeterinarioNotFoundException;
import com.peiron.JunioTFGapi.repository.AnimalRepository;
import com.peiron.JunioTFGapi.repository.CompraRepository;
import com.peiron.JunioTFGapi.repository.CrianzaRepository;
import com.peiron.JunioTFGapi.repository.VeterinarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrianzaServiceImpl implements CrianzaService {

    @Autowired
    CrianzaRepository crianzaRepository;

    @Autowired
    AnimalRepository animalRepository;

    @Autowired
    CompraRepository compraRepository;

    @Autowired
    VeterinarioRepository veterinarioRepository;


    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Crianza addCrianza(Crianza crianza, long animalId, long compraId, long veterinarioId)
            throws AnimalNotFoundException, CompraNotFoundException, VeterinarioNotFoundException {
        Animal animal = animalRepository.findById(animalId)
                    .orElseThrow(AnimalNotFoundException::new);
        crianza.setCrianzaAnimal(animal);

        Compra compra = compraRepository.findById(compraId)
                .orElseThrow(CompraNotFoundException::new);
        crianza.setCrianzaCompra((List<Compra>) compra);

        Veterinario veterinario = veterinarioRepository.findById(veterinarioId)
                .orElseThrow(VeterinarioNotFoundException::new);
        crianza.setCrianzaVeterinario(veterinario);


        return crianzaRepository.save(crianza);
    }


    @Override
    public List<Crianza> findAll() {
        return crianzaRepository.findAll();
    }

    @Override
    public Crianza findById(long id) throws CrianzaNotFoundException {
        return crianzaRepository.findById(id).orElseThrow(CrianzaNotFoundException::new);
    }

    @Override
    public void deleteCrianza(long id) throws CrianzaNotFoundException {
        Crianza crianza = crianzaRepository.findById(id).orElseThrow(CrianzaNotFoundException::new);
        crianzaRepository.delete(crianza);
    }

    @Override
    public Crianza modifyCrianza(long id, long animalId, long compraId, long veterinarioId, Crianza newCrianza)
            throws CrianzaNotFoundException, AnimalNotFoundException, CompraNotFoundException, VeterinarioNotFoundException {

        Animal existingAnimal = animalRepository.findById(animalId)
                .orElseThrow(AnimalNotFoundException::new);
        newCrianza.setCrianzaAnimal(existingAnimal);

        Compra existingCompra = compraRepository.findById(id)
                .orElseThrow(CompraNotFoundException::new);
        newCrianza.setId(id);
        modelMapper.map(newCrianza, existingCompra);

        Veterinario existingVeterinario = veterinarioRepository.findById(id)
                .orElseThrow(VeterinarioNotFoundException::new);
        newCrianza.setId(id);
        modelMapper.map(newCrianza, existingVeterinario);

        Crianza existingCrianza = crianzaRepository.findById(id)
                .orElseThrow(CrianzaNotFoundException::new);
        newCrianza.setId(id);
        modelMapper.map(newCrianza, existingVeterinario);

        return crianzaRepository.save(existingCrianza);
    }

}

package com.peiron.JunioTFGapi.service;

import com.peiron.JunioTFGapi.domain.Animal;
import com.peiron.JunioTFGapi.domain.Compra;
import com.peiron.JunioTFGapi.domain.Crianza;
import com.peiron.JunioTFGapi.domain.Recurso;
import com.peiron.JunioTFGapi.exception.AnimalNotFoundException;
import com.peiron.JunioTFGapi.exception.CompraNotFoundException;
import com.peiron.JunioTFGapi.exception.CrianzaNotFoundException;
import com.peiron.JunioTFGapi.exception.RecursoNotFoundException;
import com.peiron.JunioTFGapi.repository.CompraRepository;
import com.peiron.JunioTFGapi.repository.CrianzaRepository;
import com.peiron.JunioTFGapi.repository.RecursoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompraServiceImpl implements CompraService{

    @Autowired
    CompraRepository compraRepository;

    @Autowired
    RecursoRepository recursoRepository;

    @Autowired
    CrianzaRepository crianzaRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public Compra addCompra(Compra compra, long recursoId, long crianzaId)
            throws CrianzaNotFoundException, RecursoNotFoundException {
        Crianza crianza = crianzaRepository.findById(crianzaId)
                .orElseThrow(CrianzaNotFoundException::new);
        compra.setCompraCrianza(crianza);

        Recurso recurso = recursoRepository.findById(recursoId)
                .orElseThrow(RecursoNotFoundException::new);
        compra.setCompraRecurso((List<Recurso>) recurso);

        return compraRepository.save(compra);
    }


    @Override
    public List<Compra> findAll(){return compraRepository.findAll();}

    @Override
    public void deleteCompra(long id) throws CompraNotFoundException {
        Compra compra = compraRepository.findById(id)
                .orElseThrow(CompraNotFoundException::new);
        compraRepository.delete(compra);
    }

    @Override
    public Compra modifyCompra(long id, long crianzaId, long recursoId, Compra newCompra) throws CompraNotFoundException,  CrianzaNotFoundException, RecursoNotFoundException {

        Crianza existingCrianza = crianzaRepository.findById(crianzaId)
                .orElseThrow(CrianzaNotFoundException::new);
        newCompra.setCompraCrianza(existingCrianza);

        Recurso existingRecurso = recursoRepository.findById(recursoId)
                .orElseThrow(RecursoNotFoundException::new);
        newCompra.setCompraRecurso((List<Recurso>) existingRecurso);

        Compra existingCompra = compraRepository.findById(id)
                .orElseThrow(CompraNotFoundException::new);
        newCompra.setId(id);
        modelMapper.map(newCompra, existingCompra);

        return compraRepository.save(existingCompra);
    }

    @Override
    public Compra findById(long id) throws CompraNotFoundException {
        return compraRepository.findById(id).orElseThrow(CompraNotFoundException::new);
    }


}

package com.peiron.JunioTFGapi.service;

import com.peiron.JunioTFGapi.domain.Compra;
import com.peiron.JunioTFGapi.exception.*;

import java.util.List;

public interface CompraService {

    List<Compra> findAll();

    Compra addCompra(Compra compra, long crianzaId, long recursoId) throws RecursoNotFoundException, CrianzaNotFoundException;;

    void deleteCompra(long id) throws CompraNotFoundException;

    Compra modifyCompra(long id, long crianzaId, long recursoId, Compra compra) throws CompraNotFoundException, CrianzaNotFoundException, RecursoNotFoundException;

    Compra findById(long id) throws CompraNotFoundException;


}

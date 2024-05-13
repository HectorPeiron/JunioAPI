package com.peiron.JunioTFGapi.repository;

import com.peiron.JunioTFGapi.domain.Crianza;
import com.peiron.JunioTFGapi.domain.Recurso;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CrianzaRepository extends CrudRepository<Crianza, Long> {
    List<Crianza> findAll();
}
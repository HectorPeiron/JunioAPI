package com.peiron.JunioTFGapi.repository;

import com.peiron.JunioTFGapi.domain.TipoAnimal;
import com.peiron.JunioTFGapi.domain.TipoBaja;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipoBajaRepository extends CrudRepository<TipoBaja, Long> {
    List<TipoBaja> findAll();
}

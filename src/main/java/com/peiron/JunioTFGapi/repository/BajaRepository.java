package com.peiron.JunioTFGapi.repository;


import com.peiron.JunioTFGapi.domain.Baja;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BajaRepository extends CrudRepository<Baja, Long> {
    List<Baja> findAll();

    List<Baja> findByBajaCrianza_Id(Long crianzaId);

}

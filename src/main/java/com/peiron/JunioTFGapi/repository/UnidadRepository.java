package com.peiron.JunioTFGapi.repository;

import com.peiron.JunioTFGapi.domain.Unidad;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnidadRepository extends CrudRepository<Unidad, Long> {
    List<Unidad> findAll();
}

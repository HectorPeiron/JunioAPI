package com.peiron.JunioTFGapi.repository;


import com.peiron.JunioTFGapi.domain.Recurso;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecursoRepository extends CrudRepository<Recurso, Long> {
    List<Recurso> findAll();
}

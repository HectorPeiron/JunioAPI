package com.peiron.JunioTFGapi.repository;

import com.peiron.JunioTFGapi.domain.TipoRecurso;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipoRecursoRepository extends CrudRepository<TipoRecurso, Long> {
    List<TipoRecurso> findAll();
}

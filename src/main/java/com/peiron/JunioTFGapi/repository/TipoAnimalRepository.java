package com.peiron.JunioTFGapi.repository;

import com.peiron.JunioTFGapi.domain.TipoAnimal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipoAnimalRepository extends CrudRepository<TipoAnimal, Long> {
    List<TipoAnimal> findAll();
}

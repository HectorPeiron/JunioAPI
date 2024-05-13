package com.peiron.JunioTFGapi.repository;

import com.peiron.JunioTFGapi.domain.Veterinario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VeterinarioRepository extends CrudRepository<Veterinario, Long> {
    List<Veterinario> findAll();
}


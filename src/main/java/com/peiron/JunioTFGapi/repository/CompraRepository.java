package com.peiron.JunioTFGapi.repository;

import com.peiron.JunioTFGapi.domain.Compra;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompraRepository extends CrudRepository<Compra, Long> {
    List<Compra> findAll();
}

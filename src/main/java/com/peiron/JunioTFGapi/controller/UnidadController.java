package com.peiron.JunioTFGapi.controller;

import com.peiron.JunioTFGapi.domain.Unidad;
import com.peiron.JunioTFGapi.exception.UnidadNotFoundException;
import com.peiron.JunioTFGapi.service.UnidadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UnidadController {

    @Autowired
    UnidadService unidadService;

    private final Logger logger = LoggerFactory.getLogger(UnidadController.class);


    /*********************************************
     *                                           *
     *    Controlador de Recurosos               *
     *                                           *
     *                                           *
     *********************************************/

    @GetMapping("/unidades")
    public ResponseEntity<List<Unidad>> getUnidades() {
        return ResponseEntity.ok(unidadService.findAll());
    }


    //AÑADIR UNIDADES
    @PostMapping("/unidades")
    public ResponseEntity<Unidad> addUnidad(@Valid @RequestBody Unidad unidad) {
        logger.debug("COMIENZO DENTRO DEL ADD UNIDADES");
        Unidad newUnidad = unidadService.addUnidad(unidad);
        logger.debug("FINAL DENTRO DEL ADD UNIDADES");
        return ResponseEntity.status(HttpStatus.CREATED).body(newUnidad);
    }

    //BUSCAR UNIDADES POR ID
    @GetMapping("unidades/{id}")
    public ResponseEntity<Unidad> getUnidad(@PathVariable long id) throws UnidadNotFoundException {
        logger.debug("COMIENZO DENTRO DEL GET UNIDADES POR ID");
        Unidad unidad = unidadService.findById(id);
        logger.debug("FINAL DENTRO DEL GET UNIDADES POR ID");
        return ResponseEntity.ok(unidad);
    }
}

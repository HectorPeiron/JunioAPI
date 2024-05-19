package com.peiron.JunioTFGapi.controller;

import com.peiron.JunioTFGapi.domain.TipoAnimal;
import com.peiron.JunioTFGapi.domain.Unidad;
import com.peiron.JunioTFGapi.exception.TipoAnimalNotFoundException;
import com.peiron.JunioTFGapi.exception.UnidadNotFoundException;
import com.peiron.JunioTFGapi.service.TipoAnimalService;
import com.peiron.JunioTFGapi.service.UnidadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class TipoAnimalController {

    @Autowired
    TipoAnimalService tipoAnimalService;

    private final Logger logger = LoggerFactory.getLogger(TipoAnimalController.class);


    /*********************************************
     *                                           *
     *    Controlador de TipoAnimal              *
     *                                           *
     *                                           *
     *********************************************/

    //AÑADIR TIPO ANIMAL
    @PostMapping("/tipoAnimales")
    public ResponseEntity<TipoAnimal> addTipoAnimal(@Valid @RequestBody TipoAnimal tipoAnimal) {
        logger.debug("COMIENZO DENTRO DEL ADD TIPO ANIMAL");
        TipoAnimal newTipoAnimal = tipoAnimalService.addTipoAnimal(tipoAnimal);
        logger.debug("FINAL DENTRO DEL ADD TIPO ANIMAL");
        return ResponseEntity.status(HttpStatus.CREATED).body(newTipoAnimal);
    }

    //BUSCAR TIPO ANIMAL POR ID
    @GetMapping("tipoAnimales/{id}")
    public ResponseEntity<TipoAnimal> getTipoAnimal(@PathVariable long id) throws TipoAnimalNotFoundException {
        logger.debug("COMIENZO DENTRO DEL GET TIPO ANIMAL POR ID");
        TipoAnimal tipoAnimal = tipoAnimalService.findById(id);
        logger.debug("FINAL DENTRO DEL GET TIPO ANIMAL POR ID");
        return ResponseEntity.ok(tipoAnimal);
    }
}

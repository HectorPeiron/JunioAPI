package com.peiron.JunioTFGapi.controller;

import com.peiron.JunioTFGapi.domain.TipoRecurso;
import com.peiron.JunioTFGapi.domain.Unidad;
import com.peiron.JunioTFGapi.exception.TipoRecursoNotFoundException;
import com.peiron.JunioTFGapi.service.TipoRecursoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TipoRecursoController {

    @Autowired
    TipoRecursoService tipoRecursoService;

    private final Logger logger = LoggerFactory.getLogger(TipoRecursoController.class);


    /*********************************************
     *                                           *
     *    Controlador de TipoRecurso             *
     *                                           *
     *                                           *
     *********************************************/
    @GetMapping("/tipoRecursos")
    public ResponseEntity<List<TipoRecurso>> getTipoRecursos() {
        return ResponseEntity.ok(tipoRecursoService.findAll());
    }

    //AÑADIR TIPO RECURSOS
    @PostMapping("/tipoRecursos")
    public ResponseEntity<TipoRecurso> addTipoRecurso(@Valid @RequestBody TipoRecurso tipoRecurso) {
        logger.debug("COMIENZO DENTRO DEL ADD TIPO RECURSO");
        TipoRecurso newTipoRecurso = tipoRecursoService.addTipoRecurso(tipoRecurso);
        logger.debug("FINAL DENTRO DEL ADD TIPO RECURSO");
        return ResponseEntity.status(HttpStatus.CREATED).body(newTipoRecurso);
    }

    //BUSCAR TIPO RECURSO POR ID
    @GetMapping("tipoRecursos/{id}")
    public ResponseEntity<TipoRecurso> getTipoRecurso(@PathVariable long id) throws TipoRecursoNotFoundException {
        logger.debug("COMIENZO DENTRO DEL GET TIPO RECURSO POR ID");
        TipoRecurso tipoRecurso = tipoRecursoService.findById(id);
        logger.debug("FINAL DENTRO DEL GET TIPO RECURSO POR ID");
        return ResponseEntity.ok(tipoRecurso);
    }
}

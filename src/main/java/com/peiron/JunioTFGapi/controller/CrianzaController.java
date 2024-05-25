package com.peiron.JunioTFGapi.controller;


import com.peiron.JunioTFGapi.domain.Crianza;
import com.peiron.JunioTFGapi.exception.*;
import com.peiron.JunioTFGapi.service.CrianzaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CrianzaController {


    /*********************************************
     *                                           *
     *    Controlador de Crianzas                *
     *                                           *
     *                                           *
     *********************************************/


    @Autowired
    CrianzaService crianzaService;

    private final Logger logger = LoggerFactory.getLogger(CrianzaController.class);

    //AÑADIR CRIANZAS
    @PostMapping("/crianzas")
    public ResponseEntity<Crianza> addCrianza(@RequestBody Crianza crianza)
    {
        logger.debug("COMIENZO DENTRO DEL ADD CRIANZA");
        Crianza newCrianza = crianzaService.addCrianza(crianza);
        logger.debug(" FINAL DEL ADD CRIANZA ");
        return new ResponseEntity<>(newCrianza, HttpStatus.CREATED);
    }

    //BORRAR CRIANZA
    @DeleteMapping("/crianzas/{id}")
    public ResponseEntity<Void> deleteCrianza(@PathVariable long id) throws CrianzaNotFoundException {
        logger.debug("COMIENZO DENTRO DEL BORRAR ANIMAL");
        crianzaService.deleteCrianza(id);
        logger.debug("BORRADO DENTRO DEL BORRAR ANIMAL");
        return ResponseEntity.noContent().build();
    }

    //MODIFICAR CRIANZA
    @PutMapping("/crianzas/{id}")
    public ResponseEntity<Crianza> modifyCrianza(@PathVariable long id,
                                               @RequestBody Crianza crianza)
            throws CrianzaNotFoundException {
        logger.debug("COMIENZO DENTRO DEL MODIFICAR CRIANZA");
        Crianza modifiedCrianza = crianzaService.modifyCrianza(id, crianza);
        logger.debug("COMIENZO DENTRO DEL MODIFICAR CRIANZA");
        return ResponseEntity.status(HttpStatus.OK).body(modifiedCrianza);
    }


    //GET ALL CRIANZA
    @GetMapping("/crianzas")
    public ResponseEntity<List<Crianza>> getCrianzas() {
        return ResponseEntity.ok(crianzaService.findAll());
    }



    /*********************************************
     *                                           *
     *    Sección de manejo de excepciones       *
     *                                           *
     *                                           *
     *********************************************/


    // NOT FOUND 404

    @ExceptionHandler(CrianzaNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleCrianzaNotFoundException(CrianzaNotFoundException cnfe) {
        logger.error((cnfe.getMessage()), cnfe);
        ErrorMessage errorMessage = new ErrorMessage(404, cnfe.getMessage());
        return new ResponseEntity(errorMessage, HttpStatus.NOT_FOUND);
    }

    // ERROR 400

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleBadRequestException(MethodArgumentNotValidException manve) {
        logger.error((manve.getMessage()), manve);
        Map<String, String> errors = new HashMap<>();
        manve.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });

        ErrorMessage errorMessage = new ErrorMessage(400, "Bad Request");
        return new ResponseEntity<>(errorMessage,HttpStatus.BAD_REQUEST);
    }

    // ERROR 500

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleException(Exception e) {
        logger.error((e.getMessage()), e);
        ErrorMessage errorMessage = new ErrorMessage(500, "Internal Server Error");
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}

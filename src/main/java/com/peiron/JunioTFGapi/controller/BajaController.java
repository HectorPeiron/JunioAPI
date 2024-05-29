package com.peiron.JunioTFGapi.controller;


import com.peiron.JunioTFGapi.domain.Baja;
import com.peiron.JunioTFGapi.exception.BajaNotFoundException;
import com.peiron.JunioTFGapi.exception.CrianzaNotFoundException;
import com.peiron.JunioTFGapi.exception.ErrorMessage;

import com.peiron.JunioTFGapi.exception.TipoBajaNotFoundException;
import com.peiron.JunioTFGapi.service.BajaService;
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
public class BajaController {


    /*********************************************
     *                                           *
     *    Controlador de Baja                *
     *                                           *
     *                                           *
     *********************************************/


    @Autowired
    BajaService bajaService;

    private final Logger logger = LoggerFactory.getLogger(BajaController.class);


    @PostMapping("/bajas")
    public ResponseEntity<Baja> addBaja(@Valid @RequestBody Baja baja)
            throws CrianzaNotFoundException, TipoBajaNotFoundException {
        logger.debug("COMIENZO DENTRO DEL ADD BAJA");

        if (baja.getBajaTipoBaja() == null || baja.getBajaCrianza() == null) {
            throw new IllegalArgumentException("TipoBaja y Crianza no pueden ser null");
        }

        Long tipoBajaId = baja.getBajaTipoBaja().getId();
        Long crianzaId = baja.getBajaCrianza().getId();
        Baja newBaja = bajaService.addBaja(baja, tipoBajaId, crianzaId);
        logger.debug("FINAL DEL ADD BAJA");
        return ResponseEntity.status(HttpStatus.CREATED).body(newBaja);
    }

    //BORRAR BAJA
    @DeleteMapping("/bajas/{id}")
    public ResponseEntity<Void> deleteBaja(@PathVariable long id) throws BajaNotFoundException {
        logger.debug("COMIENZO DENTRO DEL BORRAR BAJA");
        bajaService.deleteBaja(id);
        logger.debug("BORRADO DENTRO DEL BORRAR BAJA");
        return ResponseEntity.noContent().build();
    }

    //MODIFICAR BAJA
    @PutMapping("/bajas/{id}/{tipoBajaId}/{crianzaId}")
    public ResponseEntity<Baja> modifyBaja(@PathVariable long id,
                                               @PathVariable long tipoBajaId,
                                               @PathVariable long crianzaId,
                                               @Valid @RequestBody Baja baja)
            throws BajaNotFoundException, CrianzaNotFoundException, TipoBajaNotFoundException {
        logger.debug("Inicio de la modificación del baja con ID: {}", id);
        Baja modifiedBaja = bajaService.modifyBaja(id, crianzaId, tipoBajaId, baja);
        logger.debug("Final de la modificación del Baja con ID: {}", id);
        return ResponseEntity.status(HttpStatus.OK).body(modifiedBaja);
    }


    //GET ALL BAJAS
    @GetMapping("/bajas")
    public ResponseEntity<List<Baja>> getBajas() {
        return ResponseEntity.ok(bajaService.findAll());
    }



    //GET BAJAS POR ID CRIANZA
    @GetMapping("/bajas/crianza/{crianzaId}")
    public ResponseEntity<List<Baja>> getBajasByCrianzaId(@PathVariable Long crianzaId) throws CrianzaNotFoundException {
        List<Baja> bajas = bajaService.findBajasByCrianzaId(crianzaId);
        return ResponseEntity.ok(bajas);
    }

    /*********************************************
     *                                           *
     *    Sección de manejo de excepciones       *
     *                                           *
     *                                           *
     *********************************************/


    // NOT FOUND 404

    @ExceptionHandler(BajaNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleBajaNotFoundException(BajaNotFoundException bnfe) {
        logger.error((bnfe.getMessage()), bnfe);
        ErrorMessage errorMessage = new ErrorMessage(404, bnfe.getMessage());
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

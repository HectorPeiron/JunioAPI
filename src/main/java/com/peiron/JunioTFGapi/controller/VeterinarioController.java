package com.peiron.JunioTFGapi.controller;


import com.peiron.JunioTFGapi.domain.Animal;
import com.peiron.JunioTFGapi.domain.Veterinario;
import com.peiron.JunioTFGapi.exception.*;
import com.peiron.JunioTFGapi.service.VeterinarioService;
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
public class VeterinarioController {

    /*********************************************
     *                                           *
     *    Controlador de veterinarios            *
     *                                           *
     *                                           *
     *********************************************/


    @Autowired
    VeterinarioService veterinarioService;

    private final Logger logger = LoggerFactory.getLogger(VeterinarioController.class);

    //AÑADIR VETERINARIOS
    @PostMapping("/crianzas/{crianzaId}/veterinarios")
    public ResponseEntity<Veterinario> addVeterinario(@Valid @PathVariable("crianzaId") long crianzaId,
                                                      @RequestBody Veterinario veterinario)
            throws CrianzaNotFoundException {
        logger.debug("COMIENZO DENTRO DEL ADD VETERINARIO");
        Veterinario newVeterinario = veterinarioService.addVeterinario(veterinario,crianzaId );
        logger.debug(" FINAL DEL ADD VETERINARIO ");
        return new ResponseEntity<>(newVeterinario, HttpStatus.CREATED);
    }

    //BORRAR VETERINARIOS
    @DeleteMapping("/veterinarios/{id}")
    public ResponseEntity<Void> deleteVeterinrio(@PathVariable long id) throws VeterinarioNotFoundException {
        logger.debug("COMIENZO DENTRO DEL BORRAR VETERINARIO");
        veterinarioService.deleteVeterinario(id);
        logger.debug("BORRADO DENTRO DEL BORRAR VETERINARIO");
        return ResponseEntity.noContent().build();
    }

    //MODIFICAR VETERINARIO
    @PutMapping("/veterinarios/{id}/{crianzaId}")
    public ResponseEntity<Veterinario> modifyVeterinario(@PathVariable long id,
                                               @PathVariable("crianzaId") long crianzaId,
                                               @RequestBody Veterinario veterinario)
            throws  CrianzaNotFoundException, VeterinarioNotFoundException{
        logger.debug("COMIENZO DENTRO DEL MODIFICAR VETERINARIO");
        Veterinario modifiedVeterinario = veterinarioService.modifyVeterinario(id, crianzaId, veterinario);
        logger.debug("COMIENZO DENTRO DEL MODIFICAR VETERINARIO");
        return ResponseEntity.status(HttpStatus.OK).body(modifiedVeterinario);
    }


    //GET ALL ANIMALES
    @GetMapping("/veterinarios")
    public ResponseEntity<List<Veterinario>> getVeterinarios() {
        return ResponseEntity.ok(veterinarioService.findAll());
    }


    /*********************************************
     *                                           *
     *    Sección de manejo de excepciones       *
     *                                           *
     *                                           *
     *********************************************/



    @ExceptionHandler(VeterinarioNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleVeterinarioNotFoundException(VeterinarioNotFoundException vnfe) {
        logger.error((vnfe.getMessage()), vnfe);
        ErrorMessage errorMessage = new ErrorMessage(404, vnfe.getMessage());
        return new ResponseEntity(errorMessage, HttpStatus.NOT_FOUND);
    }

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

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleException(Exception e) {
        logger.error((e.getMessage()), e);
        ErrorMessage errorMessage = new ErrorMessage(500, "Internal Server Error");
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

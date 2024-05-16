package com.peiron.JunioTFGapi.controller;


import com.peiron.JunioTFGapi.domain.Recurso;
import com.peiron.JunioTFGapi.exception.*;
import com.peiron.JunioTFGapi.service.RecursoService;
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
public class RecursoController {


    /*********************************************
     *                                           *
     *    Controlador de Recurosos               *
     *                                           *
     *                                           *
     *********************************************/


    /** Faltaria añadir y buscar en caso de ser necesario
     *
     * */


    @Autowired
    RecursoService recursoService;

    private final Logger logger = LoggerFactory.getLogger(RecursoController.class);

    //AÑADIR RECURSOS

    @PostMapping("/tipoRecursos/{tipoRecursoId}/tipoAnimales/{tipoAnimalId}/unidades/{unidadId}/recursos")
    public ResponseEntity<Recurso> addRecurso(@Valid @PathVariable("tipoRecursoId") long tipoRecursoId,
                                              @Valid @PathVariable("tipoAnimalId") long tipoAnimalId,
                                              @Valid @PathVariable("unidadId") long unidadId,

                                              @RequestBody Recurso recurso)
            throws TipoRecursoNotFoundException, TipoAnimalNotFoundException, UnidadNotFoundException {
        logger.debug("COMIENZO DENTRO DEL ADD RECURSOS");
        Recurso newRecurso = recursoService.addRecurso(recurso, tipoRecursoId,tipoAnimalId,unidadId );
        logger.debug(" FINAL DEL ADD RECURSOS ");
        return new ResponseEntity<>(newRecurso, HttpStatus.CREATED);
    }



    //BORRAR RECURSOS
    @DeleteMapping("/recusos/{id}")
    public ResponseEntity<Void> deleteRecurso(@PathVariable long id) throws RecursoNotFoundException {
        logger.debug("COMIENZO DENTRO DEL BORRAR RECURSO");
        recursoService.deleteRecurso(id);
        logger.debug("BORRADO DENTRO DEL BORRAR RECURSO");
        return ResponseEntity.noContent().build();
    }

    //MODIFICAR RECURSOS
    @PutMapping("/recursos/{id}/{tipoRecursoId}/{tipoAnimalId}/{unidadId}")
    public ResponseEntity<Recurso> modifyCrianza(@PathVariable long id,
                                                 @PathVariable("tipoRecursoId") long tipoRecursoId,
                                                 @PathVariable("tipoAnimalId") long tipoAnimalId,
                                                 @PathVariable("unidadId") long unidadId,
                                                 @RequestBody Recurso recurso)
            throws RecursoNotFoundException, TipoRecursoNotFoundException, TipoAnimalNotFoundException, UnidadNotFoundException  {
        logger.debug("COMIENZO DENTRO DEL MODIFICAR RECURSOS");
        Recurso modifiedRecurso = recursoService.modifyRecurso(id, tipoRecursoId, tipoAnimalId, unidadId, recurso);
        logger.debug("COMIENZO DENTRO DEL MODIFICAR RECURSOS");
        return ResponseEntity.status(HttpStatus.OK).body(modifiedRecurso);
    }

    //GET ALL RECURSOS
    @GetMapping("/recursos")
    public ResponseEntity<List<Recurso>> getRecursos() {
        return ResponseEntity.ok(recursoService.findAll());
    }



    /*********************************************
     *                                           *
     *    Sección de manejo de excepciones       *
     *                                           *
     *                                           *
     *********************************************/



    @ExceptionHandler(RecursoNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleRecursoNotFoundException(RecursoNotFoundException rnfe) {
        logger.error((rnfe.getMessage()), rnfe);
        ErrorMessage errorMessage = new ErrorMessage(404, rnfe.getMessage());
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



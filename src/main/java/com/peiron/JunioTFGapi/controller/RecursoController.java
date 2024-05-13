package com.peiron.JunioTFGapi.controller;


import com.peiron.JunioTFGapi.domain.Recurso;
import com.peiron.JunioTFGapi.exception.ErrorMessage;
import com.peiron.JunioTFGapi.exception.RecursoNotFoundException;
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





    //BORRAR RECURSOS
    @DeleteMapping("/recusos/{id}")
    public ResponseEntity<Void> deleteRecurso(@PathVariable long id) throws RecursoNotFoundException {
        logger.debug("COMIENZO DENTRO DEL BORRAR RECURSO");
        recursoService.deleteRecurso(id);
        logger.debug("BORRADO DENTRO DEL BORRAR RECURSO");
        return ResponseEntity.noContent().build();
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



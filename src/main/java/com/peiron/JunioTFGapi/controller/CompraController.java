package com.peiron.JunioTFGapi.controller;


import com.peiron.JunioTFGapi.domain.Animal;
import com.peiron.JunioTFGapi.domain.Compra;
import com.peiron.JunioTFGapi.exception.*;
import com.peiron.JunioTFGapi.service.CompraService;
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
import java.util.stream.Collectors;

@RestController
public class CompraController {



    /*********************************************
     *                                           *
     *    Controlador de Compras                 *
     *                                           *
     *                                           *
     *********************************************/


    @Autowired
    CompraService compraService;

    private final Logger logger = LoggerFactory.getLogger(CompraController.class);




    @PostMapping("/compras")
    public ResponseEntity<Compra> addCompra(@Valid @RequestBody Compra compra)
            throws CrianzaNotFoundException, RecursoNotFoundException {
        logger.debug("COMIENZO DENTRO DEL ADD COMPRA");

        if (compra.getCompraCrianza() == null || compra.getCompraRecurso() == null || compra.getCompraRecurso().isEmpty()) {
            throw new IllegalArgumentException("Crianza y Recursos no pueden ser null o vacíos");
        }

        Long crianzaId = compra.getCompraCrianza().getId();
        List<Long> recursoIds = compra.getCompraRecurso().stream().map(recurso -> recurso.getId()).collect(Collectors.toList());
        Compra newCompra = compraService.addCompra(compra, recursoIds, crianzaId);
        logger.debug("FINAL DEL ADD COMPRA");
        return ResponseEntity.status(HttpStatus.CREATED).body(newCompra);
    }






    //BORRAR COMPRA
    @DeleteMapping("/compras/{id}")
    public ResponseEntity<Void> deleteCompra(@PathVariable long id) throws CompraNotFoundException {
        logger.debug("COMIENZO DENTRO DEL BORRAR Compra");
        compraService.deleteCompra(id);
        logger.debug("BORRADO DENTRO DEL BORRAR Compra");
        return ResponseEntity.noContent().build();
    }

/**
    //MODIFICAR COMPRA
    @PutMapping("/compras/{id}")
    public ResponseEntity<Compra> modifyCompra(@PathVariable long id,
                                               @PathVariable("recursoId") long recursoId,
                                               @PathVariable("crianzaId") long crianzaId,
                                               @RequestBody Compra compra)
            throws CompraNotFoundException, CrianzaNotFoundException ,RecursoNotFoundException {
        logger.debug("COMIENZO DENTRO DEL MODIFICAR COMPRA");
        Compra modifiedCompra = compraService.modifyCompra(id, crianzaId, recursoId , compra);
        logger.debug("COMIENZO DENTRO DEL MODIFICAR COMPRA");
        return ResponseEntity.status(HttpStatus.OK).body(modifiedCompra);
    }

 */
    //GET ALL COMPRA
    @GetMapping("/compras")
    public ResponseEntity<List<Compra>> getCompras() {
        return ResponseEntity.ok(compraService.findAll());
    }


    /*********************************************
     *                                           *
     *    Sección de manejo de excepciones       *
     *                                           *
     *                                           *
     *********************************************/



    @ExceptionHandler(CompraNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleCompraNotFoundException(CompraNotFoundException cnfe) {
        logger.error((cnfe.getMessage()), cnfe);
        ErrorMessage errorMessage = new ErrorMessage(404, cnfe.getMessage());
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

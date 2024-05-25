package com.peiron.JunioTFGapi.controller;

import com.peiron.JunioTFGapi.domain.Animal;
import com.peiron.JunioTFGapi.exception.AnimalNotFoundException;
import com.peiron.JunioTFGapi.exception.CrianzaNotFoundException;
import com.peiron.JunioTFGapi.exception.ErrorMessage;
import com.peiron.JunioTFGapi.exception.TipoAnimalNotFoundException;
import com.peiron.JunioTFGapi.service.AnimalService;
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
public class AnimalController {


    /*********************************************
     *                                           *
     *    Controlador de Animales                *
     *                                           *
     *                                           *
     *********************************************/


    @Autowired
    AnimalService animalService;

    private final Logger logger = LoggerFactory.getLogger(AnimalController.class);


    @PostMapping("/animales")
    public ResponseEntity<Animal> addAnimal(@Valid @RequestBody Animal animal)
            throws CrianzaNotFoundException, TipoAnimalNotFoundException {
        logger.debug("COMIENZO DENTRO DEL ADD ANIMAL");

        if (animal.getAnimalTipoAnimal() == null || animal.getAnimalCrianza() == null) {
            throw new IllegalArgumentException("TipoAnimal y Crianza no pueden ser null");
        }

        Long tipoAnimalId = animal.getAnimalTipoAnimal().getId();
        Long crianzaId = animal.getAnimalCrianza().getId();
        Animal newAnimal = animalService.addAnimal(animal, tipoAnimalId, crianzaId);
        logger.debug("FINAL DEL ADD ANIMAL");
        return ResponseEntity.status(HttpStatus.CREATED).body(newAnimal);
    }

    //BORRAR ANIMAL
    @DeleteMapping("/animales/{id}")
    public ResponseEntity<Void> deleteAnimal(@PathVariable long id) throws AnimalNotFoundException {
        logger.debug("COMIENZO DENTRO DEL BORRAR ANIMAL");
        animalService.deleteAnimal(id);
        logger.debug("BORRADO DENTRO DEL BORRAR ANIMAL");
        return ResponseEntity.noContent().build();
    }

    //MODIFICAR ANIMAL
    @PutMapping("/animales/{id}/{tipoAnimalId}/{crianzaId}")
    public ResponseEntity<Animal> modifyAnimal(@PathVariable long id,
                                               @PathVariable long tipoAnimalId,
                                               @PathVariable long crianzaId,
                                               @Valid @RequestBody Animal animal)
            throws AnimalNotFoundException, CrianzaNotFoundException, TipoAnimalNotFoundException {
        logger.debug("Inicio de la modificación del animal con ID: {}", id);
        Animal modifiedAnimal = animalService.modifyAnimal(id, crianzaId, tipoAnimalId, animal);
        logger.debug("Final de la modificación del animal con ID: {}", id);
        return ResponseEntity.status(HttpStatus.OK).body(modifiedAnimal);
    }


    //GET ALL ANIMALES
    @GetMapping("/animales")
    public ResponseEntity<List<Animal>> getAnimales() {
        return ResponseEntity.ok(animalService.findAll());
    }



    /*********************************************
     *                                           *
     *    Sección de manejo de excepciones       *
     *                                           *
     *                                           *
     *********************************************/


    // NOT FOUND 404

    @ExceptionHandler(AnimalNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleAnimalNotFoundException(AnimalNotFoundException anfe) {
        logger.error((anfe.getMessage()), anfe);
        ErrorMessage errorMessage = new ErrorMessage(404, anfe.getMessage());
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

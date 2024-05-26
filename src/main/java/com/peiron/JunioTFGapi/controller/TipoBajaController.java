package com.peiron.JunioTFGapi.controller;


import com.peiron.JunioTFGapi.domain.TipoBaja;
import com.peiron.JunioTFGapi.exception.TipoBajaNotFoundException;
import com.peiron.JunioTFGapi.service.TipoBajaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TipoBajaController {

    @Autowired
    TipoBajaService tipoBajaService;

    private final Logger logger = LoggerFactory.getLogger(TipoBajaController.class);

    @GetMapping("/tipoBajas")
    public ResponseEntity<List<TipoBaja>> getTipoBajas() {
        return ResponseEntity.ok(tipoBajaService.findAll());
    }
    /*********************************************
     *                                           *
     *    Controlador de TipoBaja                *
     *                                           *
     *                                           *
     *********************************************/

    //AÑADIR TIPO Baja
    @PostMapping("/tipoBajas")
    public ResponseEntity<TipoBaja> addTipoBaja(@Valid @RequestBody TipoBaja tipoBaja) {
        logger.debug("COMIENZO DENTRO DEL ADD TIPO Baja");
        TipoBaja newTipoBaja = tipoBajaService.addTipoBaja(tipoBaja);
        logger.debug("FINAL DENTRO DEL ADD TIPO Baja");
        return ResponseEntity.status(HttpStatus.CREATED).body(newTipoBaja);
    }

    //BUSCAR TIPO ANIMAL POR ID
    @GetMapping("tipoBajas/{id}")
    public ResponseEntity<TipoBaja> getTipoBaja(@PathVariable long id) throws TipoBajaNotFoundException {
        logger.debug("COMIENZO DENTRO DEL GET TIPO Baja POR ID");
        TipoBaja tipoBaja = tipoBajaService.findById(id);
        logger.debug("FINAL DENTRO DEL GET TIPO Baja POR ID");
        return ResponseEntity.ok(tipoBaja);
    }
}

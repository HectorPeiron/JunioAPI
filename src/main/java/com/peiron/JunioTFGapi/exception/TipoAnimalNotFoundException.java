package com.peiron.JunioTFGapi.exception;

public class TipoAnimalNotFoundException extends Exception{

    public TipoAnimalNotFoundException() {
        super("El tipo animal no ha sido encontrado");
    }

    public TipoAnimalNotFoundException(String message) {
        super(message);
    }
}



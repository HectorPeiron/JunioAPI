package com.peiron.JunioTFGapi.exception;

public class AnimalNotFoundException extends Exception{

    public AnimalNotFoundException() {
        super("El animal no ha sido encontrado");
    }

    public AnimalNotFoundException(String message) {
        super(message);
    }
}



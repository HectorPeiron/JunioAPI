package com.peiron.JunioTFGapi.exception;

public class BajaNotFoundException extends Exception{

    public BajaNotFoundException() {
        super("Baja no ha sido encontrado");
    }

    public BajaNotFoundException(String message) {
        super(message);
    }
}



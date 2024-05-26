package com.peiron.JunioTFGapi.exception;

public class TipoBajaNotFoundException extends Exception{

    public TipoBajaNotFoundException() {
        super("El tipo baja no ha sido encontrado");
    }

    public TipoBajaNotFoundException(String message) {
        super(message);
    }
}



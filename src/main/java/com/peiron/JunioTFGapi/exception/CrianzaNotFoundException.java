package com.peiron.JunioTFGapi.exception;

public class CrianzaNotFoundException extends Exception{

    public CrianzaNotFoundException() {
        super("La crianza no ha sido encontrada");
    }

    public CrianzaNotFoundException(String message) {
        super(message);
    }
}

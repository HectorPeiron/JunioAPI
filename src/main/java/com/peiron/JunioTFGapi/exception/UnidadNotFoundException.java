package com.peiron.JunioTFGapi.exception;

public class UnidadNotFoundException extends Exception {

    public UnidadNotFoundException() {
        super("Esa unidad no ha sido encontrado");
    }

    public UnidadNotFoundException(String message) {
        super(message);
    }
}

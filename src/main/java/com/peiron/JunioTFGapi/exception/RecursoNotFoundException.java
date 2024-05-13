package com.peiron.JunioTFGapi.exception;

public class RecursoNotFoundException extends Exception {

    public RecursoNotFoundException() {
        super("El recurso no ha sido encontrado");
    }

    public RecursoNotFoundException(String message) {
        super(message);
    }
}

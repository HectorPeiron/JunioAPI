package com.peiron.JunioTFGapi.exception;

public class TipoRecursoNotFoundException extends Exception{

    public TipoRecursoNotFoundException() {
        super("El tipo recurso no ha sido encontrado");
    }

    public TipoRecursoNotFoundException(String message) {
        super(message);
    }
}



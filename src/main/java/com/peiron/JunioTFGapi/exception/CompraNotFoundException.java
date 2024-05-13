package com.peiron.JunioTFGapi.exception;

public class CompraNotFoundException extends Exception{

    public CompraNotFoundException() {
        super("La compra no ha sido encontrada");
    }

    public CompraNotFoundException(String message) {
        super(message);
    }
}


package com.peiron.JunioTFGapi.exception;

public class VeterinarioNotFoundException extends Exception {

    public VeterinarioNotFoundException() {
        super("El veterinario no ha sido encontrado");
    }

    public VeterinarioNotFoundException(String message) {
        super(message);
    }
}

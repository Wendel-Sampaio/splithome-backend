package com.splithome.application.exceptions;

public class FamilyNotFoundException extends RuntimeException {
    public FamilyNotFoundException(String message) {
        super(message);
    }

    public FamilyNotFoundException() {
        super("Família não encontrada!");
    }

}

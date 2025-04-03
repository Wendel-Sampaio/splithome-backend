package com.splithome.application.exceptions;

public class FamilyNotFoundException extends RuntimeException {

    public FamilyNotFoundException() {
        super("Família não encontrada!");
    }

}

package com.splithome.application.exceptions;

public class EmailNotFoundException extends RuntimeException {
    public EmailNotFoundException() {
        super("Email não encontrado!");
    }
}

package com.splithome.application.exceptions;

public class EmailNotFoundException extends RuntimeException {
    public EmailNotFoundException(String message) {
        super(message);
    }

    public EmailNotFoundException() {
        super("Email não encontrado!");
    }
}

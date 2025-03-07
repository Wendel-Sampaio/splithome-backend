package com.splithome.application.exceptions;

public class DuplicateEmailException extends RuntimeException {
    public DuplicateEmailException(String message) {
        super(message);
    }

    public DuplicateEmailException() {
        super("Esse e-mail já existe!");
    }
}

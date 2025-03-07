package com.splithome.application.exceptions;

public class SimplePasswordException extends RuntimeException {
    public SimplePasswordException(String message) {
        super(message);
    }

    public SimplePasswordException() {
        super("A senha precisa ter pelo menos 8 caracteres e pelo menos um caractere especial!");
    }
}

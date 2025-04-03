package com.splithome.application.exceptions;

public class WrongPasswordException extends RuntimeException {
    public WrongPasswordException() {
        super("A senha está incorreta!");
    }
}

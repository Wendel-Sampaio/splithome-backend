package com.splithome.application.exceptions;

public class PurchaseNotFoundException extends RuntimeException {
    public PurchaseNotFoundException(String message) {
        super(message);
    }

    public PurchaseNotFoundException() {
      super("Não foi encontrado nenhum registro com o id informado");
    }
}

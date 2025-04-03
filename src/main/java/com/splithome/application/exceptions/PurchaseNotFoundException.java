package com.splithome.application.exceptions;

public class PurchaseNotFoundException extends RuntimeException {
    public PurchaseNotFoundException() {
      super("Não foi encontrado nenhum registro com o id informado");
    }
}

package com.splithome.application.exceptions;

public class PurchaserNotFoundException extends RuntimeException {
  public PurchaserNotFoundException() {
    super("O id do comprador fornecido não existe!");
  }
}

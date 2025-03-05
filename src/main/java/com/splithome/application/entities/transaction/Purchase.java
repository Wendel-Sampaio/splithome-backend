package com.splithome.application.entities.transaction;

import com.splithome.application.DTOs.TransactionDTO;

import java.util.Date;

public class Purchase extends Transaction {

    private String purchaser;
    private Date purchaseDate;

    @Override
    public void updateTransaction(TransactionDTO transactionDTO) {
        this.purchaser = transactionDTO.purchaser();
        this.purchaseDate = transactionDTO.purchaseDate();
    }
}

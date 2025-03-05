package com.splithome.application.entities.transaction;

import org.springframework.stereotype.Component;

@Component
public class TransactionFactory {
    public Transaction createTransaction(TransactionType transactionType) {
        switch (transactionType) {
            case EXPENSE:
                return new Expense();
            case PURCHASE:
                return new Purchase();
            default:
                throw new IllegalArgumentException("Tipo de transação desconhecido!");
        }
    }
}

package com.splithome.application.services;

import com.splithome.application.DTOs.TransactionDTO;
import com.splithome.application.entities.transaction.Transaction;
import com.splithome.application.entities.transaction.TransactionFactory;
import com.splithome.application.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    private TransactionFactory transactionFactory;

    @Autowired
    private TransactionRepository transactionRepository;

    public void processTransaction(Transaction transaction, TransactionDTO transactionDTO) {
        transaction.updateTransaction(transactionDTO);
    }

    public Transaction saveTransaction(TransactionDTO transactionDTO) {
        Transaction transaction = transactionFactory.createTransaction(transactionDTO.type());
        transaction.setTitle(transactionDTO.title());
        transaction.setCategory(transactionDTO.category());
        transaction.setValue(transactionDTO.value());
        transaction.setPayers(transactionDTO.payers());
        transaction.setPaymentDate(transactionDTO.paymentDate());
        transaction.setRemainingPayers(transactionDTO.remainingPayers());
        processTransaction(transaction, transactionDTO);
        return transactionRepository.save(transaction);
    }

}

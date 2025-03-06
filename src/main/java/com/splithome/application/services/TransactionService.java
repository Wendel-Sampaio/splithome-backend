package com.splithome.application.services;

import com.splithome.application.entities.transaction.Expense;
import com.splithome.application.entities.transaction.Purchase;
import com.splithome.application.repositories.ExpenseRepository;
import com.splithome.application.repositories.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private ExpenseRepository expenseRepository;

    public List<Purchase> getAllPurchases() {
        return purchaseRepository.findAll();
    }

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public String savePurchase(Purchase purchase) {
        purchaseRepository.save(purchase);
        return "Erro ao salvar no banco de dados!";
    }

    public String saveExpense(Expense expense) {
        expenseRepository.save(expense);
        return "Erro ao salvar no banco de dados!";
    }

}

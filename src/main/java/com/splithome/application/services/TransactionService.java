package com.splithome.application.services;

import com.splithome.application.entities.transaction.Expense;
import com.splithome.application.entities.transaction.Purchase;
import com.splithome.application.exceptions.PurchaseNotFoundException;
import com.splithome.application.exceptions.PurchaserNotFoundException;
import com.splithome.application.repositories.ExpenseRepository;
import com.splithome.application.repositories.PurchaseRepository;
import com.splithome.application.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransactionService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExpenseRepository expenseRepository;

    public List<Purchase> getAllPurchases() {
        return purchaseRepository.findAll();
    }

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public void savePurchase(Purchase purchase) {
        if (userRepository.existsById(purchase.getPurchaserId())) {
            purchaseRepository.save(purchase);
        } else {
            throw new PurchaserNotFoundException();
        }
    }

    public void saveExpense(Expense expense) {
        if (userRepository.existsById(expense.getResponsibleId())) {
            expenseRepository.save(expense);
        }
    }

    public Purchase updatePurchase(Purchase purchase) {
        Optional<Purchase> selectedPurchaseOptional = purchaseRepository.findById(purchase.getId());
        if (selectedPurchaseOptional.isPresent()) {
            Purchase selectedPurchase = selectedPurchaseOptional.get();
            selectedPurchase.setRemainingPayers(purchase.getRemainingPayers());
            purchaseRepository.save(selectedPurchase);
            return selectedPurchase;
        }
        return null;
    }

    public void deletePurchase(String purchaseId) {
        if (purchaseRepository.existsById(UUID.fromString(purchaseId))) {
            purchaseRepository.deleteById(UUID.fromString(purchaseId));
        } else {
            throw new PurchaseNotFoundException();
        }
    }
}

package com.splithome.application.services;

import com.splithome.application.entities.Family;
import com.splithome.application.entities.User;
import com.splithome.application.entities.transaction.Expense;
import com.splithome.application.entities.transaction.Purchase;
import com.splithome.application.exceptions.FamilyNotFoundException;
import com.splithome.application.exceptions.PurchaseNotFoundException;
import com.splithome.application.exceptions.PurchaserNotFoundException;
import com.splithome.application.repositories.ExpenseRepository;
import com.splithome.application.repositories.FamilyRepository;
import com.splithome.application.repositories.PurchaseRepository;
import com.splithome.application.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

    @Autowired
    FamilyRepository familyRepository;

    public List<Purchase> getAllPurchases() {
        return purchaseRepository.findAllByFamilyFamilyCode(this.getUserFamilyCode());
    }

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAllByFamilyCode(this.getUserFamilyCode());
    }

    public void savePurchase(Purchase purchase) {
        if (userRepository.existsById(purchase.getPurchaserId())) {
            purchase.setFamily(familyRepository.getFamilyByFamilyCode(this.getUserFamilyCode()));
            purchaseRepository.save(purchase);
        } else {
            throw new PurchaserNotFoundException();
        }
    }


    public void saveExpense(Expense expense) {
        if (userRepository.existsById(expense.getResponsibleId())) {
            expense.setFamily(familyRepository.getFamilyByFamilyCode(this.getUserFamilyCode()));
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

    private String getUserFamilyCode(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails userDetails) {
            User user = (User) userRepository.findByEmail(userDetails.getUsername());
           return user.getFamily().getFamilyCode();
        }
        throw new FamilyNotFoundException();
    }

}

package com.splithome.application.controllers;

import com.splithome.application.entities.transaction.Category;
import com.splithome.application.entities.transaction.Expense;
import com.splithome.application.entities.transaction.Purchase;
import com.splithome.application.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/expenses")
    public ResponseEntity<List<Expense>> getAllExpenses() {
        List<Expense> expenses = transactionService.getAllExpenses();
        return ResponseEntity.ok(expenses);
    }

    @GetMapping("/purchases")
    public ResponseEntity<List<Purchase>> getAllPurchases() {
        List<Purchase> purchases = transactionService.getAllPurchases();
        return ResponseEntity.ok(purchases);
    }

    @PostMapping("/new-expense")
    public ResponseEntity<Expense> saveExpense(@RequestBody Expense expense) {
        transactionService.saveExpense(expense);
        return ResponseEntity.ok(expense);
    }

    @PostMapping("/new-purchase")
    public ResponseEntity<Purchase> saveExpense(@RequestBody Purchase purchase) {
        transactionService.savePurchase(purchase);
        return ResponseEntity.ok(purchase);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        for(Category category : Category.values()) {
            categories.add(category);
        }
        return ResponseEntity.ok(categories);
    }

    @PutMapping("/update-purchase")
    public ResponseEntity<Purchase> updatePurchase(@RequestBody Purchase purchase) {
        Purchase purchaseUpdated = transactionService.updatePurchase(purchase);
        return ResponseEntity.ok(purchaseUpdated);
    }

    @DeleteMapping("/delete/{idPurchase}")
    public ResponseEntity<String> deletePurchase(@PathVariable String idPurchase) {
        transactionService.deletePurchase(idPurchase);
        return ResponseEntity.ok("Compra deletada com sucesso!");
    }
}

package com.splithome.application.DTOs;

import com.splithome.application.entities.User;
import com.splithome.application.entities.transaction.Category;
import com.splithome.application.entities.transaction.TransactionType;

import java.util.Date;
import java.util.List;

public record TransactionDTO(
        TransactionType type,
        String title,
        Category category,
        double value,
        List<User> payers,
        Date paymentDate,
        List<User> remainingPayers,
        String purchaser,
        Date purchaseDate
) {}

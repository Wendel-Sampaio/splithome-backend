package com.splithome.application.repositories;

import com.splithome.application.entities.transaction.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, UUID> {
    List<Expense> findAllByFamilyFamilyCode(String familyCode);
}

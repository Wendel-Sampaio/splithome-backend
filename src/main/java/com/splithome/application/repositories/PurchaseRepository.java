package com.splithome.application.repositories;

import com.splithome.application.entities.transaction.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, UUID> {
    List<Purchase> findAllByFamilyFamilyCode(String userFamilyCode);
}

package com.splithome.application.repositories;

import com.splithome.application.entities.Family;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FamilyRepository extends JpaRepository<Family, UUID> {

    boolean existsByFamilyCode(String generatedCode);

    Family getFamilyByFamilyCode(String familyCode);
}

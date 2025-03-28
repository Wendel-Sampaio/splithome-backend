package com.splithome.application.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.splithome.application.entities.Family;

public interface FamilyRepository extends JpaRepository<Family, UUID> {
}

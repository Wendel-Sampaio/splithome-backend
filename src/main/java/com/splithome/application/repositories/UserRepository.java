package com.splithome.application.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.splithome.application.entities.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, UUID>{
    UserDetails findByEmail(String email);

    User getUsersById(UUID id);

    List<User> findAllByFamilyFamilyCode(String userFamilyCode);
}

package com.splithome.application.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.splithome.application.entities.User;

public interface UserRepository extends JpaRepository<User, UUID>{

}

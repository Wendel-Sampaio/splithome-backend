package com.splithome.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.splithome.application.entities.User;
import com.splithome.application.repositories.UserRepository;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public String saveUser(User user) {
        userRepository.save(user);
        return "Usuário salvo";
    }

}

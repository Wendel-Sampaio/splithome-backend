package com.splithome.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.splithome.application.entities.User;
import com.splithome.application.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String saveUser(User user) {
        userRepository.save(user);
        return "Usuário salvo";
    }

}

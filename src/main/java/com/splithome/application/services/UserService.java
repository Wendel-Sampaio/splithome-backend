package com.splithome.application.services;

import com.splithome.application.DTOs.AuthenticationDTO;
import com.splithome.application.DTOs.RegisterDTO;
import com.splithome.application.DTOs.UserDTO;
import com.splithome.application.exceptions.DuplicateEmailException;
import com.splithome.application.exceptions.EmailNotFoundException;
import com.splithome.application.exceptions.SimplePasswordException;
import com.splithome.application.exceptions.WrongPasswordException;
import com.splithome.application.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.splithome.application.entities.User;
import com.splithome.application.repositories.UserRepository;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void registerUser(RegisterDTO data) {
        if (userRepository.findByEmail(data.email()) != null) {
            throw new DuplicateEmailException();
        }
        if (data.password().length() < 8 || !data.password().matches(".*[!@#$%^&*(),.?\":{}|<>].*")) {
            throw new SimplePasswordException();
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User user = new User(data.name(), data.email().toLowerCase(), encryptedPassword);
        userRepository.save(user);
    }

    public String loginUser(AuthenticationDTO data) {
        if (userRepository.findByEmail(data.email()) == null) {
            throw new EmailNotFoundException();
        }
        var emailPassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        try {
            Authentication auth = authenticationManager.authenticate(emailPassword);
            return tokenService.generateToken((User) auth.getPrincipal());
        } catch (AuthenticationException exception) {
            throw new WrongPasswordException();
        }
    }

    public UserDTO getUserById(UUID id) {
        User user = userRepository.getUsersById(id);
        return new UserDTO(user.getName(), user.getEmail(), user.getPhoneNumber(), user.getPixKey());
    }
}

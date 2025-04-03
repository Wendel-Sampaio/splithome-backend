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

import java.util.ArrayList;
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

    public void registerUser(RegisterDTO data) {
        if (userRepository.findByEmail(data.email()) != null) {
            throw new DuplicateEmailException();
        }
        if (data.password().length() < 8 || !data.password().matches(".*[!@#$%^&*(),.?\":{}|<>].*")) {
            throw new SimplePasswordException();
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User user = new User(data.name(), data.email().toLowerCase(), encryptedPassword, data.phoneNumber(), data.pixKey());
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
        return new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getPhoneNumber(), user.getPixKey());
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDTO> usersDto = new ArrayList<>();
        for (User user : users) {
            UserDTO userDto = new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getPhoneNumber(), user.getPixKey());
            usersDto.add(userDto);
        }
        return usersDto;
    }

    public UserDTO editUserById(UUID id, UserDTO userDTO) {
        User userSelected = userRepository.getUsersById(id);
        userSelected.setName(userDTO.name());
        userSelected.setEmail(userDTO.email());
        userSelected.setPhoneNumber(userDTO.phoneNumber());
        userSelected.setPixKey(userDTO.pixKey());
        userRepository.save(userSelected);
        return userDTO;
    }
}

package com.splithome.application.services;

import com.splithome.application.DTOs.AuthenticationDTO;
import com.splithome.application.DTOs.RegisterDTO;
import com.splithome.application.DTOs.UserDTO;
import com.splithome.application.entities.Family;
import com.splithome.application.exceptions.*;
import com.splithome.application.repositories.FamilyRepository;
import com.splithome.application.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

    @Autowired
    private FamilyRepository familyRepository;

    public void registerUser(RegisterDTO data) {
        if (userRepository.findByEmail(data.email()) != null) {
            throw new DuplicateEmailException();
        }
        if (data.password().length() < 8 || !data.password().matches(".*[!@#$%^&*(),.?\":{}|<>].*")) {
            throw new SimplePasswordException();
        }
        if(!familyRepository.existsByFamilyCode(data.familyCode())) {
            throw new FamilyNotFoundException();
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        Family userFamily = familyRepository.getFamilyByFamilyCode(data.familyCode());
        User user = new User(data.name(), data.email().toLowerCase(), encryptedPassword, data.phoneNumber(), data.pixKey(), userFamily);
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
        return new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getPhoneNumber(), user.getPixKey(), user.getFamily().getFamilyCode());
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAllByFamilyFamilyCode(this.getUserFamilyCode());
        List<UserDTO> usersDto = new ArrayList<>();
        for (User user : users) {
            UserDTO userDto = new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getPhoneNumber(), user.getPixKey(), user.getFamily().getFamilyCode());
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

    private String getUserFamilyCode(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails userDetails) {
            User user = (User) userRepository.findByEmail(userDetails.getUsername());
            return user.getFamily().getFamilyCode();
        }
        throw new FamilyNotFoundException();
    }
}

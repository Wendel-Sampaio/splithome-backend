package com.splithome.application.auth;

import com.splithome.application.DTOs.AuthenticationDTO;
import com.splithome.application.DTOs.LoginResponseDTO;
import com.splithome.application.DTOs.RegisterDTO;
import com.splithome.application.entities.User;
import com.splithome.application.repositories.UserRepository;
import com.splithome.application.security.TokenService;
import com.splithome.application.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("api/user/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationUserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO data) {
        String token = userService.loginUser(data);
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid RegisterDTO data){
        userService.registerUser(data);
        return ResponseEntity.ok("Usuário cadastrado com sucesso!");
    }

    @GetMapping("/listall")
    public ResponseEntity<List<User>> listAll(){
        List<User> users = this.userRepository.findAll();
        if (users.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(users);
    }

}

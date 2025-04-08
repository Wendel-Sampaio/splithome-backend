package com.splithome.application.auth;

import com.splithome.application.DTOs.AuthenticationDTO;
import com.splithome.application.DTOs.LoginResponseDTO;
import com.splithome.application.DTOs.RegisterDTO;
import com.splithome.application.DTOs.UserDTO;
import com.splithome.application.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/user")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationUserController {

    @Autowired
    private UserService userService;

    @PostMapping("/auth/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO data) {
        String token = userService.loginUser(data);
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/auth/register")
    public ResponseEntity<String> register(@RequestBody @Valid RegisterDTO data){
        userService.registerUser(data);
        return ResponseEntity.ok("Usuário cadastrado com sucesso!");
    }

    @GetMapping("/listall")
    public ResponseEntity<List<UserDTO>> listAll(){
        return ResponseEntity.ok(this.userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable UUID id) {
        return ResponseEntity.ok(this.userService.getUserById(id));
    }

    @PutMapping("/{id}/edit-user")
    public ResponseEntity<UserDTO> editUserById(@PathVariable UUID id, @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(this.userService.editUserById(id, userDTO));
    }

}

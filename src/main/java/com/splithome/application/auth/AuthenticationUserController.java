package com.splithome.application.auth;

import com.splithome.application.DTOs.AuthenticationDTO;
import com.splithome.application.DTOs.LoginResponseDTO;
import com.splithome.application.DTOs.RegisterDTO;
import com.splithome.application.entities.User;
import com.splithome.application.repositories.UserRepository;
import com.splithome.application.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;


import javax.swing.text.html.parser.Entity;
import java.util.List;

@RestController
@RequestMapping("api/user/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationUserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO data) {
        var emailPassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = authenticationManager.authenticate(emailPassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<Entity> register(@RequestBody @Valid RegisterDTO data){
        if(this.userRepository.findByEmail(data.email()) !=  null) ResponseEntity.badRequest().build();
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User user = new User(data.name(), data.email(), encryptedPassword);
        this.userRepository.save(user);
        return ResponseEntity.ok().build();
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

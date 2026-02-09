package com.yash.pizza_backend.controllers;

import com.yash.pizza_backend.Security.JwtUtil;
import com.yash.pizza_backend.dto.LoginRequestDto;
import com.yash.pizza_backend.entities.User;
import com.yash.pizza_backend.repositories.UserRepository;
import com.yash.pizza_backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:5173")
@RequestMapping("api/auth")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/register")
    public User register(@RequestBody User user){
        return userService.register(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = userRepository
                .findByEmail(request.getEmail())
                .orElseThrow();

        String token = jwtUtil.generateToken(user);

        return ResponseEntity.ok(token);
    }


    @GetMapping("/all")
    public List<User> all(){
        return userRepository.findAll();
    }
}

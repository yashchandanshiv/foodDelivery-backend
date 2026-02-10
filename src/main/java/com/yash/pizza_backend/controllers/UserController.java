package com.yash.pizza_backend.controllers;

import com.yash.pizza_backend.Security.JwtUtil;
import com.yash.pizza_backend.dto.LoginRequestDto;
import com.yash.pizza_backend.entities.User;
import com.yash.pizza_backend.repositories.UserRepository;
import com.yash.pizza_backend.services.CustomUserDetailsService;
import com.yash.pizza_backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:5173")
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    // 🔹 Register
    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.register(user);
    }

    // 🔹 Login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto request) {

        // Authenticate user
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        // Load user details
        UserDetails userDetails =
                userDetailsService.loadUserByUsername(request.getEmail());

        // Generate JWT token
        String token = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(token);
    }

    // 🔹 Get all users (for testing)
    @GetMapping("/all")
    public List<User> all() {
        return userRepository.findAll();
    }
}

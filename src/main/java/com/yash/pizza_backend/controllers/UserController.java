package com.yash.pizza_backend.controllers;

import com.yash.pizza_backend.dto.LoginRequestDto;
import com.yash.pizza_backend.entities.User;
import com.yash.pizza_backend.repositories.UserRepository;
import com.yash.pizza_backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:5173")
@RequestMapping("api/auth")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/register")
    public User register(@RequestBody User user){
        return userService.register(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto request){

        User user = userService.login(request.getEmail(), request.getPassword());
        if(user == null){
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid email or password");
        }
        return ResponseEntity.ok(user);
    }
    
    @GetMapping("/all")
    public List<User> all(){
        return userRepository.findAll();
    }
}

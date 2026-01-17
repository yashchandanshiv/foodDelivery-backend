package com.yash.pizza_backend.controllers;

import com.yash.pizza_backend.entities.User;
import com.yash.pizza_backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:5173")
@RequestMapping("api/auth")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public User register(@RequestBody User user){
        return userService.register(user);
    }

    @PostMapping("/login")
    public User login(String email,String password){
        return userService.login(email,password);
    }
}

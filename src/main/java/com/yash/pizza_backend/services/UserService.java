package com.yash.pizza_backend.services;

import com.yash.pizza_backend.entities.User;
import com.yash.pizza_backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User register(User user){
        return userRepository.save(user);
    }

    public User login(String email, String password){
        User user = userRepository.findByEmail(email)
                .orElse(null);
        if(user == null){
            return null;
        }
        if(!user.getPassword().equals(password)){
            return null;
        }
        return user;
    }

}

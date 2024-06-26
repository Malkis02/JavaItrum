package com.example.task1.controllers;

import com.example.task1.models.User;
import com.example.task1.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/users")
    public User saveUser(@RequestBody User user) {
        return userRepository.save(user);
    }


    @GetMapping("/check")
    public String checkAuth() {
        return "You're in";
    }

    @Secured("MODERATOR")
    @GetMapping("/checkModerator")
    public String checkModeratorRole(){
        return  "You're in as Moderator";
    }

    @Secured("ADMIN")
    @GetMapping("/checkAdmin")
    public String checkAdminRole(){
        return  "You're in as Admin";
    }
}

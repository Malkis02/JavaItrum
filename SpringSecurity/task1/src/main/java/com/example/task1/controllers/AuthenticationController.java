package com.example.task1.controllers;

import com.example.task1.JwtAuthenticationProvider;
import com.example.task1.JwtAuthenticationToken;
import com.example.task1.JwtUtil;
import com.example.task1.models.User;
import com.example.task1.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/token")
public class AuthenticationController {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Autowired
    private JwtAuthenticationProvider authenticationManager;

    @Autowired
    public AuthenticationController(UserRepository userRepository,JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/generate")
    public JwtAuthenticationToken generate(@RequestBody User loginUser) throws AuthenticationException {

        final User user = userRepository.findByUsername(loginUser.getUsername());
        final String token = jwtUtil.generateToken(user.getUsername());
        return new JwtAuthenticationToken(user.getUsername(), user.getPassword(),token);
    }
}
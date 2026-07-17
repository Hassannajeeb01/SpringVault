package com.example.springvault.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.springvault.dto.LoginRequestDTO;
import com.example.springvault.dto.RegisterRequestDTO;
import com.example.springvault.service.AuthService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@CrossOrigin(origins = "*")
@RestController
public class AuthController {

    private final AuthService authService;

    public AuthController (AuthService authService) {
        this.authService = authService;
    }
    
    @PostMapping("/auth/register")
    public String registerUser (@RequestBody RegisterRequestDTO registerRequestDTO) {
        return authService.registerUser(registerRequestDTO);
    }

    @PostMapping ("auth/login")
    public String loginUser (@RequestBody LoginRequestDTO loginRequestDTO) {
        return authService.loginUser(loginRequestDTO);
    }

}
 
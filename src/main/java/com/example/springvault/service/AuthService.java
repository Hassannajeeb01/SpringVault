package com.example.springvault.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.springvault.dto.LoginRequestDTO;
import com.example.springvault.dto.RegisterRequestDTO;
import com.example.springvault.exception.InvalidCredentialsException;
import com.example.springvault.model.UserEntity;
import com.example.springvault.repository.UserRepository;
import com.example.springvault.util.JwtUtil;

@Service
public class AuthService{
    
    // Service for for register (hash password, save user) and login (validate credentials, return JWT)

    // inject
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public AuthService(PasswordEncoder passwordEncoder, UserRepository userRepository, JwtUtil jwtUtil) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;

    }

    public String registerUser(RegisterRequestDTO registerRequestDTO) {
        // encode the password, save user in UserEntity
        String encodedPassword = passwordEncoder.encode(registerRequestDTO.getPassword());

        UserEntity userEntity = UserEntity.builder()
                                            .email(registerRequestDTO.getEmail())
                                            .password(encodedPassword)
                                            .build();
        
        if (userRepository.findByEmail(userEntity.getEmail()).isPresent()) {
            throw new InvalidCredentialsException();
        }
        
        userRepository.save(userEntity);     
        return jwtUtil.generateToken(registerRequestDTO.getEmail());                      
    } 

    public String loginUser (LoginRequestDTO loginRequestDTO) {
        // verify the password, then sign a JWT and return it

        // retreive user from DB based on email
        // comnpare the passwords by reverse hash
        // if matches, sign JWT and send
        // else, idk  

        UserEntity user = userRepository.findByEmail(loginRequestDTO.getEmail()).orElseThrow(() -> new InvalidCredentialsException());

        String storedPassword = user.getPassword();
        if (!passwordEncoder.matches(loginRequestDTO.getPassword(), storedPassword)) { // passwords do not match
            throw new InvalidCredentialsException();
        }
        
        return jwtUtil.generateToken(loginRequestDTO.getEmail());

    }
}

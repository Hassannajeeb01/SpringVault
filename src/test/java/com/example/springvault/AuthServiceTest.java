package com.example.springvault;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.springvault.dto.LoginRequestDTO;
import com.example.springvault.dto.RegisterRequestDTO;
import com.example.springvault.exception.InvalidCredentialsException;
import com.example.springvault.model.UserEntity;
import com.example.springvault.repository.UserRepository;
import com.example.springvault.service.AuthService;
import com.example.springvault.util.JwtUtil;

public class AuthServiceTest {

        // mock userRepository and jwtUtil and passwordEncoder
    // build authservice
    // mock RegisterRequestDTO
    // call registerUser
    // a String JWT shouild be returned, verify it by using JWT something
    
    private UserRepository userRepository = Mockito.mock();
    private JwtUtil jwtUtil = new JwtUtil();
    private PasswordEncoder passwordEncoder = Mockito.mock();
    RegisterRequestDTO registerRequestDTO = new RegisterRequestDTO();
    LoginRequestDTO loginRequestDTO = new LoginRequestDTO();

    AuthService authService = new AuthService(passwordEncoder, userRepository, jwtUtil);
    
    @BeforeEach
    void setUp() {
        registerRequestDTO = new RegisterRequestDTO();
        loginRequestDTO = new LoginRequestDTO();
    }

    @Test
    void successfullUserRegistration() {
        // Arrange
        registerRequestDTO.setEmail("hassan@test.com");
        registerRequestDTO.setPassword("password123");

        //Act
        String token = authService.registerUser(registerRequestDTO);

        // Assert, if user is successfully registered, we shoild get a valid JWT token
        assertEquals(true, jwtUtil.isTokenValid(token));

    }

    @Test
    void unsuccessfulUserRegistration() {
        // try to register the same and it should throw an exception

        // Arrange
        registerRequestDTO.setEmail("saad@test.com");
        registerRequestDTO.setPassword("password123");

        Mockito.when(userRepository.findByEmail("saad@test.com"))
       .thenReturn(Optional.of(new UserEntity()));

        // Act and Assert
        assertThrows(InvalidCredentialsException.class, () -> authService.registerUser(registerRequestDTO));
    }

    @Test
    void successfullLogin() {

        // Arrange
        loginRequestDTO.setEmail("test@test.com");
        loginRequestDTO.setPassword("password");

        Mockito.when(userRepository.findByEmail("test@test.com"))
        .thenReturn(Optional.of(UserEntity.builder().email("test@test.com").password("Hashedpassword").build()));

        Mockito.when(passwordEncoder.matches("password", "Hashedpassword"))
        .thenReturn(true);
      

        // Act

        String token = loginUser(loginRequestDTO);


        // Assert
        assertEquals(true, jwtUtil.isTokenValid(token));

    }

    public String loginUser (LoginRequestDTO loginRequestDTO) {

        UserEntity user = userRepository.findByEmail(loginRequestDTO.getEmail()).orElseThrow(() -> new InvalidCredentialsException());

        String storedPassword = user.getPassword();
        if (!passwordEncoder.matches(loginRequestDTO.getPassword(), storedPassword)) { // passwords do not match
            throw new InvalidCredentialsException();
        }
        
        return jwtUtil.generateToken(loginRequestDTO.getEmail());

    }
    
}

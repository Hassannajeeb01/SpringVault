package com.example.springvault.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegisterRequestDTO {
    private String email;
    private String password;
}

package com.example.springvault.model;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table (name = "users")
public class UserEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    private UUID id;

    @Column (nullable = false, unique = true)
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // so password does not get leaked via API, even hashed
    @Column (nullable = false, length = 60) // hashed length
    private String password;
}
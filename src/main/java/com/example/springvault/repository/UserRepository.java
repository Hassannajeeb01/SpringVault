package com.example.springvault.repository;

import com.example.springvault.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface UserRepository extends JpaRepository <UserEntity, UUID>{
    Optional<UserEntity> findByEmail(String email);
    List<UserEntity> findByEmailIn(List<String> emails);
}
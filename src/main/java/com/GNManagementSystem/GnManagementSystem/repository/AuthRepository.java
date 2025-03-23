package com.GNManagementSystem.GnManagementSystem.repository;

import com.GNManagementSystem.GnManagementSystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<User, String>{
    Boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
}

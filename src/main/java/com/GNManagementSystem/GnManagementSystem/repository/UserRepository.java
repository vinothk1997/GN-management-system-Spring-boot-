package com.GNManagementSystem.GnManagementSystem.repository;

import com.GNManagementSystem.GnManagementSystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}

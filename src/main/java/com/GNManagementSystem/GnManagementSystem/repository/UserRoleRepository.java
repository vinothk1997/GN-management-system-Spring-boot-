package com.GNManagementSystem.GnManagementSystem.repository;

import com.GNManagementSystem.GnManagementSystem.entity.User;
import com.GNManagementSystem.GnManagementSystem.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole, String> {
    List<UserRole> findByUser(User user);

}

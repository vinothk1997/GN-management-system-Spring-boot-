package com.GNManagementSystem.GnManagementSystem.converter;

import com.GNManagementSystem.GnManagementSystem.entity.UserRole;
import com.GNManagementSystem.GnManagementSystem.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserRoleConverter {

    public List<UserRole> convert(List<Role> roles) {

        return roles.stream()
                .map(role -> UserRole.builder()
                        .role(role)
                        .build())
                .toList();
    }

}

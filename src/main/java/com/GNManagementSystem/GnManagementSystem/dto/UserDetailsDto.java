package com.GNManagementSystem.GnManagementSystem.dto;

import com.GNManagementSystem.GnManagementSystem.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDetailsDto {
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private List<Role> roles;
}

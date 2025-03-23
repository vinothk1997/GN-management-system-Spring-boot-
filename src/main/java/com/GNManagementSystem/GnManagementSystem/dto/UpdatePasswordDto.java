package com.GNManagementSystem.GnManagementSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UpdatePasswordDto {
    private String email;
    private String currentPassword;
    private String newPassword;
    private String confirmPassword;
    private String token;
}

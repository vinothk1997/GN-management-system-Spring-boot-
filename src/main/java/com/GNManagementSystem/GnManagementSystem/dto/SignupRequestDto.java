package com.GNManagementSystem.GnManagementSystem.dto;

import com.GNManagementSystem.GnManagementSystem.enums.Gender;
import com.GNManagementSystem.GnManagementSystem.enums.Role;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequestDto {
    private String firstName;
    private String lastName;
    private String username;
    private Gender gender;
    private Integer age;
    private LocalDate dateOfBirth;
    private String city;
    private String password;
    private String email;
    private String phone;
    private String address;
    private List<Role> roles;
}
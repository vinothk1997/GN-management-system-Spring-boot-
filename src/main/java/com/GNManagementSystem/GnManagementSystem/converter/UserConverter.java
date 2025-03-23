package com.GNManagementSystem.GnManagementSystem.converter;

import com.GNManagementSystem.GnManagementSystem.constants.ApplicationConstants;
import com.GNManagementSystem.GnManagementSystem.dto.SignupRequestDto;
import com.GNManagementSystem.GnManagementSystem.dto.UserDto;
import com.GNManagementSystem.GnManagementSystem.entity.User;
import com.GNManagementSystem.GnManagementSystem.entity.UserRole;
import com.GNManagementSystem.GnManagementSystem.exception.ServiceException;
import com.GNManagementSystem.GnManagementSystem.repository.AuthRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class UserConverter {
    private final PasswordEncoder passwordEncoder;
    private final AuthRepository authRepository;
    private final UserRoleConverter userRoleConverter;

    public UserDto convert(User user) {
        return UserDto.builder()
                .userId(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phoneNo(user.getPhone())
                .address(user.getAddress())
                .build();
    }


    public User convertToUser(SignupRequestDto signupRequestDto) {
        if (Boolean.TRUE.equals(authRepository.existsByEmail(signupRequestDto.getEmail()))) {
            throw new ServiceException(ApplicationConstants.EMAIL_ALREADY_IN_USE, "Email already in use", HttpStatus.BAD_REQUEST);
        }

        return User.builder()
                .firstName(signupRequestDto.getFirstName())
                .lastName(signupRequestDto.getLastName())
                .username(signupRequestDto.getUsername())
                .email(signupRequestDto.getEmail())
                .phone(signupRequestDto.getPhone())
                .address(signupRequestDto.getAddress())
                .city(signupRequestDto.getCity())
                .age(signupRequestDto.getAge())
                .gender(signupRequestDto.getGender())
                .dateOfBirth(signupRequestDto.getDateOfBirth())
                .password(passwordEncoder.encode(signupRequestDto.getPassword()))
                .userRoles(new ArrayList<>())
                .build();
    }
}

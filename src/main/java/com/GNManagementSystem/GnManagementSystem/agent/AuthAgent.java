package com.GNManagementSystem.GnManagementSystem.agent;

import com.GNManagementSystem.GnManagementSystem.converter.UserConverter;
import com.GNManagementSystem.GnManagementSystem.dto.*;
import com.GNManagementSystem.GnManagementSystem.entity.User;
import com.GNManagementSystem.GnManagementSystem.entity.UserRole;
import com.GNManagementSystem.GnManagementSystem.repository.UserRoleRepository;
import com.GNManagementSystem.GnManagementSystem.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class AuthAgent {
    private final AuthService authService;
    private final UserConverter userConverter;
    private final UserRoleRepository userRoleRepository;
    public ResponseDto registerUser(SignupRequestDto signupRequestDto) {
        User user = userConverter.convertToUser(signupRequestDto);
        User savedUser = authService.registerUser(user);

        List<UserRole> userRoles =  signupRequestDto.getRoles().stream().map(role->{
            UserRole userRole = new UserRole();
            userRole.setUser(savedUser);
            userRole.setRole(role);
            return userRole;
        }).toList();

        userRoleRepository.saveAll(userRoles);

        return  new ResponseDto("saved");
    }

    public UserDetailsDto loginUser(LoginRequestDto loginRequestDto, HttpServletRequest request) {
        return authService.loginUser(loginRequestDto, request);
    }


    public ResponseDto resetPasswordLink(String email) {
        return authService.resetPasswordLink(email);
    }

    public ResponseDto updatePassword(UpdatePasswordDto updatePasswordDto) {
        return authService.updatePassword(updatePasswordDto);
    }

    public UserDetailsDto googleLogin(OAuth2User user,HttpServletRequest httpServletRequest) {
        return authService.googleLogin(user,httpServletRequest);
    }
}

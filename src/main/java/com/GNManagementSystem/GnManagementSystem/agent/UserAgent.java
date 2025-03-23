package com.GNManagementSystem.GnManagementSystem.agent;

import com.GNManagementSystem.GnManagementSystem.converter.UserConverter;
import com.GNManagementSystem.GnManagementSystem.dto.ResponseDto;
import com.GNManagementSystem.GnManagementSystem.dto.SignupRequestDto;
import com.GNManagementSystem.GnManagementSystem.dto.UserDto;
import com.GNManagementSystem.GnManagementSystem.entity.User;
import com.GNManagementSystem.GnManagementSystem.service.AuthService;
import com.GNManagementSystem.GnManagementSystem.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserAgent {
    private final UserService userService;
    public UserDto getUser(String id) {
        return userService.getUser(id);
    }


}

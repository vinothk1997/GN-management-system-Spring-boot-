package com.GNManagementSystem.GnManagementSystem.service;

import com.GNManagementSystem.GnManagementSystem.converter.UserConverter;
import com.GNManagementSystem.GnManagementSystem.dto.UserDto;
import com.GNManagementSystem.GnManagementSystem.entity.User;
import com.GNManagementSystem.GnManagementSystem.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserConverter userConverter;

    public UserDto getUser(String id) {
        User user= userRepository.findById(id).orElseThrow(() -> new RuntimeException("User Not Found"));
        return userConverter.convert(user);
    }


}

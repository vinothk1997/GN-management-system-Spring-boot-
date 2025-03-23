package com.GNManagementSystem.GnManagementSystem.controller;

import com.GNManagementSystem.GnManagementSystem.agent.AuthAgent;
import com.GNManagementSystem.GnManagementSystem.agent.UserAgent;
import com.GNManagementSystem.GnManagementSystem.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user")
@AllArgsConstructor
public class UserController {
    private final UserAgent userAgent;
    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable("id") String id) {
        return userAgent.getUser(id);
    }
}

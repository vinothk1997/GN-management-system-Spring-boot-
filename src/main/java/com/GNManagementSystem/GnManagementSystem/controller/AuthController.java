package com.GNManagementSystem.GnManagementSystem.controller;

import com.GNManagementSystem.GnManagementSystem.agent.AuthAgent;
import com.GNManagementSystem.GnManagementSystem.dto.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthController {
    private final AuthAgent authAgent;
    @PostMapping("/signup")
    public ResponseDto registerUser(@RequestBody SignupRequestDto signupRequestDto) {
        return authAgent.registerUser(signupRequestDto);
    }

    @PostMapping("/login")
    public UserDetailsDto loginUser(@RequestBody LoginRequestDto loginRequestDto, HttpServletRequest request, HttpServletResponse httpServletResponse) {
        return authAgent.loginUser(loginRequestDto,request,httpServletResponse);
    }

    @GetMapping("/forget-password-link")
    public ResponseDto resetPasswordLink(@RequestParam("email") String email){
        return authAgent.resetPasswordLink(email);
    }

    @PostMapping("/password")
    public ResponseDto updatePassword(@RequestBody UpdatePasswordDto updatePasswordDto){
        return authAgent.updatePassword(updatePasswordDto);
    }

    @GetMapping("/current-user")
    public Object getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            return "Logged in user: " + authentication.getPrincipal().toString();
        }
        return "No authenticated user found";
    }

    @GetMapping("/oauth2/success")
    public Map<String, Object> getUser(@AuthenticationPrincipal OAuth2User principal) {
        if (principal == null) {
            return Map.of("error", "User not authenticated");
        }
        return principal.getAttributes(); // Return Google user details
    }


    @GetMapping("/google/login")
    public UserDetailsDto googleLogin(@AuthenticationPrincipal OAuth2User user,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {
        return authAgent.googleLogin(user,httpServletRequest,httpServletResponse);
    }
}

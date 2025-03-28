package com.GNManagementSystem.GnManagementSystem.controller;

import com.GNManagementSystem.GnManagementSystem.agent.AuthAgent;
import com.GNManagementSystem.GnManagementSystem.constants.ApplicationConstants;
import com.GNManagementSystem.GnManagementSystem.dto.*;
import com.GNManagementSystem.GnManagementSystem.entity.User;
import com.GNManagementSystem.GnManagementSystem.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
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
        return authAgent.loginUser(loginRequestDto, request, httpServletResponse);
    }

    @GetMapping("/forget-password-link")
    public ResponseDto resetPasswordLink(@RequestParam("email") String email) {
        return authAgent.resetPasswordLink(email);
    }

    @PostMapping("/password")
    public ResponseDto updatePassword(@RequestBody UpdatePasswordDto updatePasswordDto) {
        return authAgent.updatePassword(updatePasswordDto);
    }

    @GetMapping("/current-user")
    public Object getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            return "Logged in user: " + authentication.getPrincipal().toString();
        }
        return "No authenticated user found";
    }

    @GetMapping("/me")
    public ResponseEntity<UserDetailsDto> getUserFromSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            throw new ServiceException("Session not found", ApplicationConstants.BAD_REQUEST, HttpStatus.UNAUTHORIZED);
        }

        SecurityContext securityContext = (SecurityContext) session.getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
        if (securityContext == null || securityContext.getAuthentication() == null ||
                !(securityContext.getAuthentication().getPrincipal() instanceof User user)) {
            throw new ServiceException("User not authenticated", ApplicationConstants.BAD_REQUEST, HttpStatus.UNAUTHORIZED);
        }

        UserDetailsDto userDetails = UserDetailsDto.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .roles(List.of(user.getUserRoles().get(0).getRole()))
                .build();

        return ResponseEntity.ok(userDetails);
    }

    @GetMapping("/oauth2/success")
    public Map<String, Object> getUser(@AuthenticationPrincipal OAuth2User principal) {
        if (principal == null) {
            return Map.of("error", "User not authenticated");
        }
        return principal.getAttributes(); // Return Google user details
    }


    @GetMapping("/google/login")
    public void googleLogin(@AuthenticationPrincipal OAuth2User user, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        try {
            UserDetailsDto userDetailsDto = authAgent.googleLogin(user, httpServletRequest, httpServletResponse);
            log.info("user detail,{}", userDetailsDto);
            httpServletResponse.sendRedirect("http://localhost:4200/login?email="+userDetailsDto.getEmail()+"&isVerified="+Boolean.TRUE);


        } catch (ServiceException ex) {
            if (ex.getMessage().contains("Email not found")) {
                httpServletResponse.sendRedirect("http://localhost:4200/login");
                throw ex;
            }
        }
    }
}

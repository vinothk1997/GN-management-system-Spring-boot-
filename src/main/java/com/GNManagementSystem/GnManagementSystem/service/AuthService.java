package com.GNManagementSystem.GnManagementSystem.service;

import com.GNManagementSystem.GnManagementSystem.constants.ApplicationConstants;
import com.GNManagementSystem.GnManagementSystem.dto.LoginRequestDto;
import com.GNManagementSystem.GnManagementSystem.dto.ResponseDto;
import com.GNManagementSystem.GnManagementSystem.dto.UpdatePasswordDto;
import com.GNManagementSystem.GnManagementSystem.dto.UserDetailsDto;
import com.GNManagementSystem.GnManagementSystem.entity.User;
import com.GNManagementSystem.GnManagementSystem.entity.UserRole;
import com.GNManagementSystem.GnManagementSystem.exception.ServiceException;
import com.GNManagementSystem.GnManagementSystem.repository.AuthRepository;
import com.GNManagementSystem.GnManagementSystem.repository.UserRepository;
import com.GNManagementSystem.GnManagementSystem.repository.UserRoleRepository;
import com.GNManagementSystem.GnManagementSystem.utill.EmailService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    @Value("${app.reset-url}")
    private  String appResetUrl;
    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final HttpSession session;
    private final EmailService emailService;
    private final UserRoleRepository userRoleRepository;


    public User registerUser(User user) {
        return authRepository.save(user);
    }

    public UserDetailsDto loginUser(@RequestBody LoginRequestDto request, HttpServletRequest httpRequest) {
        Optional<User> userOptional = authRepository.findByEmail(request.getEmail());

        if (userOptional.isEmpty() || !passwordEncoder.matches(request.getPassword(), userOptional.get().getPassword())) {
            throw new ServiceException("Invalid Credential",ApplicationConstants.INVALID_CREDENTIAL,HttpStatus.FORBIDDEN);
        }

        User user = userOptional.get();

        var authorities=  userRoleRepository.findByUser(user).stream()
                .map(userRole -> new SimpleGrantedAuthority("ROLE_"+userRole.getRole().name()))
                .toList();


        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, authorities);

        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);

        httpRequest.getSession(true);
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, securityContext);


        return UserDetailsDto.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .roles(user.getUserRoles().stream().map(UserRole::getRole).toList())
                .build();

    }


    public String generateResetToken(String email) {
        User user = authRepository.findByEmail(email)
                .orElseThrow(() -> new ServiceException(ApplicationConstants.NOT_FOUND,"User not found", HttpStatus.NOT_FOUND));
        String token = UUID.randomUUID().toString();
        user.setResetToken(token);
        authRepository.save(user);
        return token;
    }

    public ResponseDto resetPasswordLink(String email) {
        Optional<User> userOptional = authRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            return ResponseDto.builder()
                    .message("User with this email does not exist")
                    .build();
        }
        User user = userOptional.get();

        String token = generateResetToken(email);
        String resetUrl = appResetUrl+token + "&email=" + email;

        emailService.sendHtmlEmail(email, user.getFirstName(), resetUrl);

        return ResponseDto.builder()
                .message("Reset Link is sent successfully")
                .build();
    }


    public ResponseDto updatePassword(UpdatePasswordDto updatePasswordDto) {
        log.info("emil,{}",updatePasswordDto);
        User user = authRepository.findByEmail(updatePasswordDto.getEmail())
                .orElseThrow(() -> new ServiceException("User not found",ApplicationConstants.NOT_FOUND,HttpStatus.NOT_FOUND));

        if (user.getResetToken() == null || !user.getResetToken().equals(updatePasswordDto.getToken())) {
            throw new ServiceException(ApplicationConstants.INVALID_CREDENTIAL,"Invalid or expired reset token",HttpStatus.BAD_REQUEST);
        }

        if (!updatePasswordDto.getNewPassword().equals(updatePasswordDto.getConfirmPassword())) {
            throw new ServiceException(ApplicationConstants.INVALID_CREDENTIAL,"Passwords do not match",HttpStatus.BAD_REQUEST);
        }

        user.setPassword(passwordEncoder.encode(updatePasswordDto.getNewPassword()));
        user.setResetToken(null);
        authRepository.save(user);

        return ResponseDto.builder()
                .message("Password updated successfully")
                .build();
    }

}

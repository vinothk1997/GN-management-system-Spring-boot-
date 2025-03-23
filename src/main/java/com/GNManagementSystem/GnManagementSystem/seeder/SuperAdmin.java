package com.GNManagementSystem.GnManagementSystem.seeder;

import com.GNManagementSystem.GnManagementSystem.entity.User;
import com.GNManagementSystem.GnManagementSystem.entity.UserRole;
import com.GNManagementSystem.GnManagementSystem.enums.Gender;
import com.GNManagementSystem.GnManagementSystem.enums.Role;
import com.GNManagementSystem.GnManagementSystem.repository.AuthRepository;
import com.GNManagementSystem.GnManagementSystem.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class SuperAdmin implements CommandLineRunner {
    private final PasswordEncoder passwordEncoder;
    private final AuthRepository authRepository;
    @Override
    public void run(String... args) throws Exception {
        if (Boolean.TRUE.equals(authRepository.existsByEmail("admin@gmail.com"))) {
            log.info("Admin already exists");
            return;
        }

        User user = User.builder()
                .firstName("Admin")
                .lastName("Admin")
                .email("admin@gmail.com")
                .password(passwordEncoder.encode("admin"))
                .phone("0767777777")
                .dateOfBirth(LocalDate.of(1997, 4, 16))
                .gender(Gender.FEMALE)
                .age(28)
                .address("Poonakary")
                .city("Kilinochchi")
                .nic("1891544445555")
                .build();

        UserRole userRole = UserRole.builder()
                .role(Role.SUPER_ADMIN)
                .user(user)
                .build();

        user.setUserRoles(List.of(userRole));
        authRepository.save(user);
        log.info("Admin successfully added");
    }
}

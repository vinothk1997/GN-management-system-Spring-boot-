package com.GNManagementSystem.GnManagementSystem.converter;

import com.GNManagementSystem.GnManagementSystem.dto.DivisionalSecretariatDto;
import com.GNManagementSystem.GnManagementSystem.dto.DivisionalSecretariatResponseDto;
import com.GNManagementSystem.GnManagementSystem.dto.GramaNiladhariDto;
import com.GNManagementSystem.GnManagementSystem.entity.DivisionalSecretariat;
import com.GNManagementSystem.GnManagementSystem.entity.EducationalQualification;
import com.GNManagementSystem.GnManagementSystem.entity.GramaNiladhari;
import com.GNManagementSystem.GnManagementSystem.entity.UserRole;
import com.GNManagementSystem.GnManagementSystem.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DivisionalSecretariatConverter {
    private final PasswordEncoder passwordEncoder;
    private final EducationalQualificationConverter educationalQualificationConverter;
    public DivisionalSecretariat convertToDivisionalSecretariat(DivisionalSecretariatDto divisionalSecretariatDto) {
        DivisionalSecretariat divisionalSecretariat = DivisionalSecretariat.builder()
                .id(divisionalSecretariatDto.getId())
                .username(divisionalSecretariatDto.getUsername())
                .password(passwordEncoder.encode(divisionalSecretariatDto.getPassword()))
                .firstName(divisionalSecretariatDto.getFirstName())
                .lastName(divisionalSecretariatDto.getLastName())
                .gender(divisionalSecretariatDto.getGender())
                .age(divisionalSecretariatDto.getAge())
                .dateOfBirth(divisionalSecretariatDto.getDateOfBirth())
                .email(divisionalSecretariatDto.getEmail())
                .phone(divisionalSecretariatDto.getPhone())
                .address(divisionalSecretariatDto.getAddress())
                .city(divisionalSecretariatDto.getCity())
                .jobCardNo(divisionalSecretariatDto.getJobCardNo())
                .serviceDuration(divisionalSecretariatDto.getServiceGrade())
                .serviceGrade(divisionalSecretariatDto.getServiceGrade())
                .build();

        UserRole useRole = UserRole.builder()
                .role(Role.DS)
                .user(divisionalSecretariat)
                .build();

        divisionalSecretariat.setUserRoles(Collections.singletonList(useRole));

        List<EducationalQualification> qualifications = divisionalSecretariatDto.getEducationQualification() != null ?
                divisionalSecretariatDto.getEducationQualification().stream()
                        .map(dto -> {
                            EducationalQualification qualification = educationalQualificationConverter.convertEducationalQualification(dto);
                            qualification.setUser(divisionalSecretariat);
                            return qualification;
                        })
                        .toList() :
                Collections.emptyList();
        divisionalSecretariat.setEducationalQualifications(qualifications);

        return divisionalSecretariat;
    }

    public DivisionalSecretariatResponseDto convertToDivisionalSecretariatResponseDto(DivisionalSecretariat divisionalSecretariat) {
        return DivisionalSecretariatResponseDto.builder()
                .id(divisionalSecretariat.getId())
                .username(divisionalSecretariat.getUsername())
                .firstName(divisionalSecretariat.getFirstName())
                .lastName(divisionalSecretariat.getLastName())
                .gender(divisionalSecretariat.getGender())
                .age(divisionalSecretariat.getAge())
                .dateOfBirth(divisionalSecretariat.getDateOfBirth())
                .email(divisionalSecretariat.getEmail())
                .phone(divisionalSecretariat.getPhone())
                .address(divisionalSecretariat.getAddress())
                .city(divisionalSecretariat.getCity())
                .jobCardNo(divisionalSecretariat.getJobCardNo())
                .serviceDuration(divisionalSecretariat.getServiceGrade())
                .serviceGrade(divisionalSecretariat.getServiceGrade())
                .build();
    }
}

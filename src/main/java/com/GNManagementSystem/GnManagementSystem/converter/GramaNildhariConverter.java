package com.GNManagementSystem.GnManagementSystem.converter;

import com.GNManagementSystem.GnManagementSystem.dto.GramaNiladhariDto;
import com.GNManagementSystem.GnManagementSystem.dto.GramaNiladhariResponseDto;
import com.GNManagementSystem.GnManagementSystem.entity.EducationalQualification;
import com.GNManagementSystem.GnManagementSystem.entity.GramaNiladhari;
import com.GNManagementSystem.GnManagementSystem.entity.User;
import com.GNManagementSystem.GnManagementSystem.entity.UserRole;
import com.GNManagementSystem.GnManagementSystem.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GramaNildhariConverter {
    private final PasswordEncoder passwordEncoder;
    private final EducationalQualificationConverter educationalQualificationConverter;
    public GramaNiladhari convertToGramaNiladhari(GramaNiladhariDto gramaNiladhariDto) {
        GramaNiladhari gramaNiladhari = GramaNiladhari.builder()
                .username(gramaNiladhariDto.getUsername())
                .password(passwordEncoder.encode(gramaNiladhariDto.getPassword()))
                .firstName(gramaNiladhariDto.getFirstName())
                .lastName(gramaNiladhariDto.getLastName())
                .gender(gramaNiladhariDto.getGender())
                .age(gramaNiladhariDto.getAge())
                .dateOfBirth(gramaNiladhariDto.getDateOfBirth())
                .email(gramaNiladhariDto.getEmail())
                .phone(gramaNiladhariDto.getPhone())
                .address(gramaNiladhariDto.getAddress())
                .city(gramaNiladhariDto.getCity())
                .jobCardNo(gramaNiladhariDto.getJobCardNo())
                .serviceGrade(gramaNiladhariDto.getServiceGrade())
                .build();

        UserRole userRole = UserRole.builder()
                .user(gramaNiladhari)
                .role(Role.GS)
                .build();

        List<EducationalQualification> qualifications = (gramaNiladhariDto.getEducationQualification() != null) ?
                gramaNiladhariDto.getEducationQualification().stream()
                        .map(dto -> {
                            EducationalQualification qualification = educationalQualificationConverter.convertEducationalQualification(dto);
                            qualification.setUser(gramaNiladhari);
                            return qualification;
                        })
                        .toList() :
                Collections.emptyList();
        gramaNiladhari.setEducationalQualifications(qualifications);
        gramaNiladhari.setUserRoles(Collections.singletonList(userRole));

        return gramaNiladhari;
    }

    public GramaNiladhariResponseDto convertToGramaNiladhariResponseDto(GramaNiladhari gramaNiladhari) {
        return GramaNiladhariResponseDto.builder()
                .id(gramaNiladhari.getId())
                .username(gramaNiladhari.getUsername())
                .firstName(gramaNiladhari.getFirstName())
                .lastName(gramaNiladhari.getLastName())
                .gender(gramaNiladhari.getGender())
                .age(gramaNiladhari.getAge())
                .dateOfBirth(gramaNiladhari.getDateOfBirth())
                .email(gramaNiladhari.getEmail())
                .phone(gramaNiladhari.getPhone())
                .address(gramaNiladhari.getAddress())
                .city(gramaNiladhari.getCity())
                .jobCardNo(gramaNiladhari.getJobCardNo())
                .serviceGrade(gramaNiladhari.getServiceGrade())
                .educationQualification(gramaNiladhari.getEducationalQualifications().stream().map(educationalQualificationConverter::convertEducationalQualificationDto).toList())
                .build();
    }


}

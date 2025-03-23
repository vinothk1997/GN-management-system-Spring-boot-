package com.GNManagementSystem.GnManagementSystem.dto;

import com.GNManagementSystem.GnManagementSystem.enums.Gender;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DivisionalSecretariatDto {
    private String id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private Integer age;
    private LocalDate dateOfBirth;
    private String email;
    private String phone;
    private String address;
    private String city;
    private String jobCardNo;
    private String serviceGrade;
    private Boolean isPermanent;
    private List<EducationQualificationDto> educationQualification;
}

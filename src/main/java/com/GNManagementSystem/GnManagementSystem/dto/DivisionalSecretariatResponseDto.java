package com.GNManagementSystem.GnManagementSystem.dto;

import com.GNManagementSystem.GnManagementSystem.enums.Gender;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DivisionalSecretariatResponseDto {
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
    private String serviceDuration;
    private Boolean isPermanent;
}

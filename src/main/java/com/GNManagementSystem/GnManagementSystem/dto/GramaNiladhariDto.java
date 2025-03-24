package com.GNManagementSystem.GnManagementSystem.dto;

import com.GNManagementSystem.GnManagementSystem.enums.Gender;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Pattern;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GramaNiladhariDto {
    private String id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Pattern(regexp = "^(\\d{12}|\\d{9}[VX])$", message = "Invalid NIC format. Use 12 digits or 10 digits ending with 'V' or 'X'.")
    private String nic;
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

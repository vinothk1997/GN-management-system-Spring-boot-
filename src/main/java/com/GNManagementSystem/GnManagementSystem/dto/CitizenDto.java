package com.GNManagementSystem.GnManagementSystem.dto;

import com.GNManagementSystem.GnManagementSystem.enums.Gender;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CitizenDto {
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
    private Boolean father;
    private Boolean mother;
    private Boolean child;
    private String familyCardNo;
    private String gramaNiladhariDivisionId;

    public CitizenDto( String username, String firstName, String lastName,
                      Gender gender, Integer age, LocalDate dateOfBirth, String familyCardNo) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.dateOfBirth = dateOfBirth;
        this.familyCardNo = familyCardNo;
    }
}

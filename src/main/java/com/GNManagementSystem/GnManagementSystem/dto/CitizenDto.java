package com.GNManagementSystem.GnManagementSystem.dto;

import com.GNManagementSystem.GnManagementSystem.enums.Gender;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Pattern;
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
    @Pattern(regexp = "^(\\d{12}|\\d{9}[VX])$", message = "Invalid NIC format. Use 12 digits or 10 digits ending with 'V' or 'X'.")
    private String nic;
    private LocalDate dateOfBirth;
    private String email;
    private String phone;
    private String address;
    private String city;
    private Boolean father;
    private Boolean mother;
    private Boolean child;
    private String familyCardNo;
    private String gramaNiladhariId;

    public CitizenDto( String username, String firstName, String lastName,
                      Gender gender, Integer age, LocalDate dateOfBirth, String familyCardNo,String email) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.dateOfBirth = dateOfBirth;
        this.familyCardNo = familyCardNo;
        this.email= email;
    }
}

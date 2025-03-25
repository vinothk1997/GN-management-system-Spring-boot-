package com.GNManagementSystem.GnManagementSystem.dto;

import com.GNManagementSystem.GnManagementSystem.enums.IncomeType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class IncomeCertificateResponseDetailDto {
    private String incomeCertificateId;
    private String firstName;
    private String lastName;
    private String email;
    private String streetAddress;
    private String city;
    private String incomeId;
    @Enumerated(EnumType.STRING)
    private IncomeType incomeType;
    private String incomeSource;
    private BigDecimal incomeAmount;
    private String gnFirstName;
    private String gnLastName;
    private String gnDivisionName;
    private String gnDivisionAddress;
    private String serialNo;

}

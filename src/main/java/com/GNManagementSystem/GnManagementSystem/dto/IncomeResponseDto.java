package com.GNManagementSystem.GnManagementSystem.dto;


import com.GNManagementSystem.GnManagementSystem.enums.IncomeType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IncomeResponseDto {
    @Enumerated(EnumType.STRING)
    private String id;
    private IncomeType incomeType;
    private String incomeSource;
    private BigDecimal incomeValue;
    private String citizenId;
}

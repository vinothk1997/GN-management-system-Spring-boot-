package com.GNManagementSystem.GnManagementSystem.dto;

import com.GNManagementSystem.GnManagementSystem.enums.IncomeType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IncomeDto {
    private String id;
    @Enumerated(EnumType.STRING)
    private IncomeType incomeType;
    private String incomeSource;
    private BigDecimal incomeValue;
}

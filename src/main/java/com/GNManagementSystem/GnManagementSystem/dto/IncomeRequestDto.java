package com.GNManagementSystem.GnManagementSystem.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IncomeRequestDto {
    private String citizenId;
    private List<IncomeDto> incomes;
}
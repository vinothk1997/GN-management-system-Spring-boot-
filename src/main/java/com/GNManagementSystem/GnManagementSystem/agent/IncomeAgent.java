package com.GNManagementSystem.GnManagementSystem.agent;

import com.GNManagementSystem.GnManagementSystem.dto.IncomeRequestDto;
import com.GNManagementSystem.GnManagementSystem.dto.IncomeResponseDto;
import com.GNManagementSystem.GnManagementSystem.dto.ResponseDto;
import com.GNManagementSystem.GnManagementSystem.service.IncomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class IncomeAgent {
    private final IncomeService incomeService;
    public ResponseDto saveIncomes(IncomeRequestDto incomeRequestDto) {
        return incomeService.saveIncome(incomeRequestDto);
    }

    public List<IncomeResponseDto> getAllIncomesByCitizenId(String citizenId) {
        return incomeService.getAllIncomesByCitizenId(citizenId);
    }

    public ResponseDto updateIncome(IncomeRequestDto incomeRequestDto) {
        return incomeService.updateIncome(incomeRequestDto);
    }
}

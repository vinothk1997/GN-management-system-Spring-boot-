package com.GNManagementSystem.GnManagementSystem.controller;

import com.GNManagementSystem.GnManagementSystem.agent.IncomeAgent;
import com.GNManagementSystem.GnManagementSystem.dto.IncomeRequestDto;
import com.GNManagementSystem.GnManagementSystem.dto.IncomeResponseDto;
import com.GNManagementSystem.GnManagementSystem.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/income")
@RequiredArgsConstructor
public class IncomeController {
    private final IncomeAgent incomeAgent;
    @PostMapping
    public ResponseDto saveIncomes(@RequestBody IncomeRequestDto incomeRequestDto) {
        return incomeAgent.saveIncomes(incomeRequestDto);
    }
    @GetMapping("/{id}")
    public List<IncomeResponseDto> getAllIncomesByCitizenId(@PathVariable("id") String citizenId) {
        return incomeAgent.getAllIncomesByCitizenId(citizenId);
    }

    @PutMapping
    public ResponseDto updateIncome(@RequestBody IncomeRequestDto incomeRequestDto) {
        return incomeAgent.updateIncome(incomeRequestDto);
    }
}

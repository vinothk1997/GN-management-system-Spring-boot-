package com.GNManagementSystem.GnManagementSystem.service;

import com.GNManagementSystem.GnManagementSystem.constants.ApplicationConstants;
import com.GNManagementSystem.GnManagementSystem.converter.IncomeConverter;
import com.GNManagementSystem.GnManagementSystem.dto.IncomeRequestDto;
import com.GNManagementSystem.GnManagementSystem.dto.IncomeResponseDto;
import com.GNManagementSystem.GnManagementSystem.dto.ResponseDto;
import com.GNManagementSystem.GnManagementSystem.entity.Income;
import com.GNManagementSystem.GnManagementSystem.enums.IncomeType;
import com.GNManagementSystem.GnManagementSystem.exception.ServiceException;
import com.GNManagementSystem.GnManagementSystem.repository.IncomeRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class IncomeService {
    private final IncomeConverter incomeConverter;
    private final IncomeRepository incomeRepository;

    public ResponseDto saveIncome(IncomeRequestDto incomeRequestDto) {

        List<Income> incomes = incomeRequestDto.getIncomes().stream().map(incomeDto -> incomeConverter.convert(incomeDto,incomeRequestDto.getCitizenId())).toList();

        List<IncomeType> existingIncomeTypes = incomeRepository.findIncomeTypeByCitizenId(incomeRequestDto.getCitizenId());

        incomes.forEach(income ->{
            if(existingIncomeTypes.contains(income.getIncomeType())) {
                throw new ServiceException("Already there "+income.getIncomeType().toString(), ApplicationConstants.ALREADY_EXIST, HttpStatus.BAD_REQUEST);
            }
        });

        incomeRepository.saveAll(incomes);
        return new ResponseDto("Incomes saved successfully");
    }

    public ResponseDto updateIncome(IncomeRequestDto incomeRequestDto) {
        List<Income> incomes = incomeRequestDto.getIncomes().stream().map(incomeDto -> incomeConverter.convertToUpdate(incomeDto,incomeRequestDto.getCitizenId())).toList();
        incomeRepository.saveAll(incomes);
        return new ResponseDto("Incomes updated successfully");
    }


    public List<IncomeResponseDto> getAllIncomesByCitizenId(String citizenId) {
        return incomeRepository.findAllByCitizenId(citizenId);
    }
}

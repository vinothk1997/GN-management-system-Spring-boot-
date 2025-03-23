package com.GNManagementSystem.GnManagementSystem.converter;

import com.GNManagementSystem.GnManagementSystem.constants.ApplicationConstants;
import com.GNManagementSystem.GnManagementSystem.dto.IncomeDto;
import com.GNManagementSystem.GnManagementSystem.entity.Citizen;
import com.GNManagementSystem.GnManagementSystem.entity.Income;
import com.GNManagementSystem.GnManagementSystem.exception.ServiceException;
import com.GNManagementSystem.GnManagementSystem.repository.CitizenRepository;
import com.GNManagementSystem.GnManagementSystem.repository.IncomeRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class IncomeConverter {
    private final CitizenRepository citizenRepository;
    public Income convert(IncomeDto incomeDto, String citizenId) {
        Citizen citizen = citizenRepository.findById(citizenId)
                .orElseThrow(() -> new ServiceException("Citizen not found", ApplicationConstants.NOT_FOUND, HttpStatus.NOT_FOUND));

        return Income.builder()
                .incomeType(incomeDto.getIncomeType())
                .incomeSource(incomeDto.getIncomeSource())
                .incomeValue(incomeDto.getIncomeValue())
                .citizen(citizen)
                .build();
    }

    public Income convertToUpdate(IncomeDto incomeDto, String citizenId) {
        Citizen citizen = citizenRepository.findById(citizenId)
                .orElseThrow(() -> new ServiceException("Citizen not found", ApplicationConstants.NOT_FOUND, HttpStatus.NOT_FOUND));

        return Income.builder()
                .id(incomeDto.getId())
                .incomeType(incomeDto.getIncomeType())
                .incomeSource(incomeDto.getIncomeSource())
                .incomeValue(incomeDto.getIncomeValue())
                .citizen(citizen)
                .build();
    }

}

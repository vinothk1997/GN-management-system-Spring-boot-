package com.GNManagementSystem.GnManagementSystem.agent;

import com.GNManagementSystem.GnManagementSystem.dto.EmploymentRequestDto;
import com.GNManagementSystem.GnManagementSystem.dto.ResponseDto;
import com.GNManagementSystem.GnManagementSystem.entity.Employment;
import com.GNManagementSystem.GnManagementSystem.service.EmploymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmploymentAgent {
    private final EmploymentService employmentService;

    public ResponseDto saveEmployment(EmploymentRequestDto employmentRequestDto) {
        return employmentService.saveEmployment(employmentRequestDto);
    }

    public ResponseDto updateEmployment(EmploymentRequestDto employmentRequestDto) {
        return employmentService.updateEmployment(employmentRequestDto);
    }
}

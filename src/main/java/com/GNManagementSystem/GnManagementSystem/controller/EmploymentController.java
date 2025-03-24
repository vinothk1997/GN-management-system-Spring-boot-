package com.GNManagementSystem.GnManagementSystem.controller;

import com.GNManagementSystem.GnManagementSystem.agent.EmploymentAgent;
import com.GNManagementSystem.GnManagementSystem.dto.EmploymentRequestDto;
import com.GNManagementSystem.GnManagementSystem.dto.ResponseDto;
import com.GNManagementSystem.GnManagementSystem.service.EmploymentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/employment")
public class EmploymentController {
    private final EmploymentAgent employmentAgent;

    @PostMapping()
    public ResponseDto saveEmployment(EmploymentRequestDto employmentRequestDto){
        return employmentAgent.saveEmployment(employmentRequestDto);
    }

    @PutMapping
    public ResponseDto updateEmployment(EmploymentRequestDto employmentRequestDto){
        return employmentAgent.updateEmployment(employmentRequestDto);
    }
}

package com.GNManagementSystem.GnManagementSystem.controller;

import com.GNManagementSystem.GnManagementSystem.agent.EmploymentAgent;
import com.GNManagementSystem.GnManagementSystem.dto.EmploymentRequestDto;
import com.GNManagementSystem.GnManagementSystem.dto.EmploymentResponseDto;
import com.GNManagementSystem.GnManagementSystem.dto.ResponseDto;
import com.GNManagementSystem.GnManagementSystem.service.EmploymentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/employment")
public class EmploymentController {
    private final EmploymentAgent employmentAgent;

    @PostMapping()
    public ResponseDto saveEmployment(@RequestBody EmploymentRequestDto employmentRequestDto){
        return employmentAgent.saveEmployment(employmentRequestDto);
    }

    @PutMapping
    public ResponseDto updateEmployment(@RequestBody EmploymentRequestDto employmentRequestDto){
        return employmentAgent.updateEmployment(employmentRequestDto);
    }

    @GetMapping("/{id}")
    public List<EmploymentResponseDto> getEmploymentByUserId(@PathVariable("id") String userId) {
        return employmentAgent.getEmploymentByUserId(userId);
    }
}

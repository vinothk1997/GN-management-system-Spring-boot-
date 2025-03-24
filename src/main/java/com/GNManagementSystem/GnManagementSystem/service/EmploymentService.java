package com.GNManagementSystem.GnManagementSystem.service;

import com.GNManagementSystem.GnManagementSystem.converter.EmploymentConverter;
import com.GNManagementSystem.GnManagementSystem.dto.EmploymentRequestDto;
import com.GNManagementSystem.GnManagementSystem.dto.ResponseDto;
import com.GNManagementSystem.GnManagementSystem.entity.Employment;
import com.GNManagementSystem.GnManagementSystem.repository.EmploymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class EmploymentService {
    private final EmploymentConverter employmentConverter;
    private final EmploymentRepository employmentRepository;
    public ResponseDto saveEmployment(EmploymentRequestDto employmentRequestDto) {
        Employment employment = employmentConverter.convert(employmentRequestDto);
        employmentRepository.save(employment);
        return new ResponseDto("Employment saved successfully");
    }

    public ResponseDto updateEmployment(EmploymentRequestDto employmentRequestDto) {
        Employment employment = employmentConverter.convertToUpdate(employmentRequestDto);
        employmentRepository.save(employment);
        return new ResponseDto("Employment updated successfully");
    }
}

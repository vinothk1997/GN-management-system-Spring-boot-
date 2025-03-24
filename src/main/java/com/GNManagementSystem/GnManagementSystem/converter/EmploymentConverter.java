package com.GNManagementSystem.GnManagementSystem.converter;

import com.GNManagementSystem.GnManagementSystem.constants.ApplicationConstants;
import com.GNManagementSystem.GnManagementSystem.dto.EmploymentRequestDto;
import com.GNManagementSystem.GnManagementSystem.entity.Employment;
import com.GNManagementSystem.GnManagementSystem.entity.Job;
import com.GNManagementSystem.GnManagementSystem.entity.User;
import com.GNManagementSystem.GnManagementSystem.exception.ServiceException;
import com.GNManagementSystem.GnManagementSystem.repository.JobRepository;
import com.GNManagementSystem.GnManagementSystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmploymentConverter {

    private final JobRepository jobRepository;
    private final UserRepository userRepository;

    public Employment convert(EmploymentRequestDto employmentRequestDto) {
        Job job = jobRepository.findById(employmentRequestDto.getJobId()).orElseThrow(()->new ServiceException("Job not found ", ApplicationConstants.NOT_FOUND, HttpStatus.NOT_FOUND));
        User user = userRepository.findById(employmentRequestDto.getUserId()).orElseThrow(()->new ServiceException("User not found ", ApplicationConstants.NOT_FOUND, HttpStatus.NOT_FOUND));
        return Employment.builder()
                .endDate(employmentRequestDto.getEndDate())
                .startDate(employmentRequestDto.getStartDate())
                .job(job)
                .user(user)
                .build();
    }

    public Employment convertToUpdate(EmploymentRequestDto employmentRequestDto) {
        Job job = jobRepository.findById(employmentRequestDto.getJobId()).orElseThrow(()->new ServiceException("Job not found ", ApplicationConstants.NOT_FOUND, HttpStatus.NOT_FOUND));
        User user = userRepository.findById(employmentRequestDto.getUserId()).orElseThrow(()->new ServiceException("User not found ", ApplicationConstants.NOT_FOUND, HttpStatus.NOT_FOUND));
        return Employment.builder()
                .id(employmentRequestDto.getId())
                .endDate(employmentRequestDto.getEndDate())
                .startDate(employmentRequestDto.getStartDate())
                .job(job)
                .user(user)
                .build();
    }

}

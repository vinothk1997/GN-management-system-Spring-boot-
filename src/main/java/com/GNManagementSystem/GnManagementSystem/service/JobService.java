package com.GNManagementSystem.GnManagementSystem.service;

import com.GNManagementSystem.GnManagementSystem.constants.ApplicationConstants;
import com.GNManagementSystem.GnManagementSystem.dto.JobDetailDto;
import com.GNManagementSystem.GnManagementSystem.dto.ResponseDto;
import com.GNManagementSystem.GnManagementSystem.entity.Job;
import com.GNManagementSystem.GnManagementSystem.exception.ServiceException;
import com.GNManagementSystem.GnManagementSystem.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JobService {
    private final JobRepository jobRepository;
    public Job save(Job job) {
        if (jobRepository.findByTitle(job.getTitle()) != null) {
            throw new ServiceException("Job already exists",ApplicationConstants.ALREADY_EXIST,
                    HttpStatus.CONFLICT);
        }
        return jobRepository.save(job);
    }

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public Job getJobById(String  id) {
        return jobRepository.findById(id).orElseThrow(()->new ServiceException("Job not found", ApplicationConstants.NOT_FOUND, HttpStatus.NOT_FOUND));
    }

    public ResponseDto deleteJobById(String id) {
        jobRepository.deleteById(id);
        return new ResponseDto("Job deleted successfully");
    }

    public ResponseDto updateJobById(Job job) {
        jobRepository.save(job);
        return new ResponseDto("Job updated successfully");
    }

    public JobDetailDto  findJobByTitle(String title) {
        return jobRepository.getJobDetailByTitle(title);
    }
}

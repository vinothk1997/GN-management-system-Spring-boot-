package com.GNManagementSystem.GnManagementSystem.agent;

import com.GNManagementSystem.GnManagementSystem.converter.JobConverter;
import com.GNManagementSystem.GnManagementSystem.dto.JobDetailDto;
import com.GNManagementSystem.GnManagementSystem.dto.JobDto;
import com.GNManagementSystem.GnManagementSystem.dto.ResponseDto;
import com.GNManagementSystem.GnManagementSystem.entity.Job;
import com.GNManagementSystem.GnManagementSystem.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class JobAgent {
    private final JobConverter jobConverter;
    private final JobService jobService;

    public Job save(JobDto jobDto) {
        Job job = jobConverter.convert(jobDto);
        return jobService.save(job);
    }

    public List<JobDto> getAllJobs() {
        List<Job> jobs = jobService.getAllJobs();
        return jobs.stream().map(jobConverter::convert).toList();
    }

    public JobDto getJobById(String  id) {
        return jobConverter.convert(jobService.getJobById(id));
    }

    public ResponseDto updateJob(JobDto jobDto) {
        Job job = jobConverter.convert(jobDto);
        return jobService.updateJobById(job);
    }

    public ResponseDto deleteJob(String id) {
        return jobService.deleteJobById(id);
    }

    public JobDetailDto getJobDetailById(String title) {
        return jobService.findJobByTitle(title);
    }
}

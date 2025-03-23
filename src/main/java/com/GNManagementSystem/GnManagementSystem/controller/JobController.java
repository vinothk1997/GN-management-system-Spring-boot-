package com.GNManagementSystem.GnManagementSystem.controller;

import com.GNManagementSystem.GnManagementSystem.agent.JobAgent;
import com.GNManagementSystem.GnManagementSystem.dto.JobDetailDto;
import com.GNManagementSystem.GnManagementSystem.dto.JobDto;
import com.GNManagementSystem.GnManagementSystem.dto.ResponseDto;
import com.GNManagementSystem.GnManagementSystem.entity.Job;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/jobs")
public class JobController {
    private final JobAgent jobAgent;
    @PostMapping
    public Job save(@RequestBody JobDto jobDto) {
        return jobAgent.save(jobDto);
    }

    @GetMapping
    public List<JobDto> getAllJobs() {
        return jobAgent.getAllJobs();
    }

    @GetMapping("/{id}")
    public JobDto getJobById(@PathVariable("id") String id) {
        return jobAgent.getJobById(id);
    }

    @PutMapping()
    public ResponseDto updateJob(@RequestBody JobDto jobDto) {
        return jobAgent.updateJob(jobDto);
    }

    @DeleteMapping("/{id}")
    public ResponseDto deleteJob(@PathVariable("id") String id) {
        return jobAgent.deleteJob(id);
    }

    @GetMapping("/by-title")
    public JobDetailDto findByTitle(@RequestParam("title") String title) {
        return jobAgent.getJobDetailById(title);
    }
}

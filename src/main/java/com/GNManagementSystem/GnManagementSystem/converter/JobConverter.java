package com.GNManagementSystem.GnManagementSystem.converter;

import com.GNManagementSystem.GnManagementSystem.dto.JobDto;
import com.GNManagementSystem.GnManagementSystem.entity.Job;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class JobConverter {
    public Job convert(JobDto jobDto) {
        return Job.builder()
                .title(jobDto.getTitle())
                .description(jobDto.getDescription())
                .build();
    }

    public JobDto convert(Job job) {
        return JobDto.builder()
                .id(job.getId())
                .title(job.getTitle())
                .description(job.getDescription())
                .build();
    }
}

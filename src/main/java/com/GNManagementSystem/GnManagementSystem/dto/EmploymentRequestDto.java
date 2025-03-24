package com.GNManagementSystem.GnManagementSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmploymentRequestDto {
    private String id;
    private LocalDate startDate;
    private LocalDate endDate;
    private String userId;
    private String jobId;
}

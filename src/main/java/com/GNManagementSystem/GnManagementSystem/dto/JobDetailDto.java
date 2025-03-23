package com.GNManagementSystem.GnManagementSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class JobDetailDto {
    private String jobId;
    private String name;
    private String description;
}

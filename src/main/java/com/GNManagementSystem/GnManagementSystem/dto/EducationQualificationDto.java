package com.GNManagementSystem.GnManagementSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EducationQualificationDto {
    private String id;
    private String qualification;
    private String institution;
    private Integer startingYear;
    private Integer completionYear;
}

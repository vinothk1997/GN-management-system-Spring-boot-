package com.GNManagementSystem.GnManagementSystem.converter;

import com.GNManagementSystem.GnManagementSystem.dto.EducationQualificationDto;
import com.GNManagementSystem.GnManagementSystem.entity.EducationalQualification;
import org.springframework.stereotype.Component;

@Component
public class EducationalQualificationConverter {

    public EducationalQualification convertEducationalQualification(EducationQualificationDto educationQualificationDto){
        return EducationalQualification.builder()
                .qualification(educationQualificationDto.getQualification())
                .institution(educationQualificationDto.getInstitution())
                .startingYear(educationQualificationDto.getStartingYear())
                .completionYear(educationQualificationDto.getCompletionYear())
                .build();
    }


    public EducationQualificationDto convertEducationalQualificationDto(EducationalQualification educationalQualification) {
        return EducationQualificationDto.builder()
                .id(educationalQualification.getId())
                .qualification(educationalQualification.getQualification())
                .institution(educationalQualification.getInstitution())
                .startingYear(educationalQualification.getStartingYear())
                .completionYear(educationalQualification.getCompletionYear())
                .build();
    }
}

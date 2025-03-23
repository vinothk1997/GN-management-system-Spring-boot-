package com.GNManagementSystem.GnManagementSystem.agent;

import com.GNManagementSystem.GnManagementSystem.converter.CitizenConverter;
import com.GNManagementSystem.GnManagementSystem.dto.CitizenDto;
import com.GNManagementSystem.GnManagementSystem.dto.ResponseDto;
import com.GNManagementSystem.GnManagementSystem.entity.Citizen;
import com.GNManagementSystem.GnManagementSystem.service.CitizenService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class CitizenAgent {
    private final CitizenConverter citizenConverter;
    private final CitizenService citizenService;

    public ResponseDto createCitizen(CitizenDto citizenDto) {
        Citizen citizen = citizenConverter.convert(citizenDto);
        return citizenService.saveCitizen(citizen);
    }

    public List<CitizenDto> getAllCitizenByFilter(String nic,String firstName,String familyCardNo,int ageFrom,int ageTo,String gnId) {
     return citizenService.getCitizenByFilter(nic,firstName,familyCardNo,ageFrom,ageTo,gnId);
    }

    public Citizen getCitizenById(String id) {
        return citizenService.getCitizenById(id);
    }
}

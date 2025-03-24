package com.GNManagementSystem.GnManagementSystem.service;

import com.GNManagementSystem.GnManagementSystem.constants.ApplicationConstants;
import com.GNManagementSystem.GnManagementSystem.dto.CitizenDto;
import com.GNManagementSystem.GnManagementSystem.dto.ResponseDto;
import com.GNManagementSystem.GnManagementSystem.entity.Citizen;
import com.GNManagementSystem.GnManagementSystem.entity.UserRole;
import com.GNManagementSystem.GnManagementSystem.enums.Role;
import com.GNManagementSystem.GnManagementSystem.exception.ServiceException;
import com.GNManagementSystem.GnManagementSystem.repository.CitizenRepository;
import com.GNManagementSystem.GnManagementSystem.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CitizenService {
    private final CitizenRepository citizenRepository;
    private final UserRoleRepository userRoleRepository;
    public ResponseDto saveCitizen(Citizen citizen) {
        if(Boolean.TRUE.equals(citizenRepository.existsByEmail(citizen.getEmail()))){
            throw new ServiceException(ApplicationConstants.ALREADY_EXIST,"email already exist",HttpStatus.BAD_REQUEST);
        }
        if(Boolean.TRUE.equals(citizenRepository.existsByNic(citizen.getNic()))){
            throw new ServiceException(ApplicationConstants.ALREADY_EXIST,"nic already exist",HttpStatus.BAD_REQUEST);
        }
        Citizen savedCitizen =citizenRepository.save(citizen);
        UserRole userRole = UserRole.builder()
                .role(Role.PEOPLE)
                .user(savedCitizen)
                .build();
        userRoleRepository.save(userRole);

        return new ResponseDto("Citizen saved successfully");
    }

    public List<Citizen> getAllCitizens() {
        return citizenRepository.findAll();
    }

    public List<CitizenDto> getCitizenByFilter(String nic,String firstname,String familyCardNo,int ageFrom,int ageTo,String gnId){
        return  citizenRepository.getCitizensByFilter(nic,firstname,familyCardNo,ageFrom,ageTo,gnId);
    }

    public Citizen getCitizenById(String id) {
        return citizenRepository.findById(id).orElseThrow(()->new ServiceException(ApplicationConstants.NOT_FOUND,"Citizen not found", HttpStatus.NOT_FOUND));
    }
}

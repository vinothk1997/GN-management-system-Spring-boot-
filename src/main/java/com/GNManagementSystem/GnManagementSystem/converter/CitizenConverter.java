package com.GNManagementSystem.GnManagementSystem.converter;

import com.GNManagementSystem.GnManagementSystem.constants.ApplicationConstants;
import com.GNManagementSystem.GnManagementSystem.dto.CitizenDto;
import com.GNManagementSystem.GnManagementSystem.entity.Citizen;
import com.GNManagementSystem.GnManagementSystem.entity.GramaNiladhari;
import com.GNManagementSystem.GnManagementSystem.entity.GramaNiladhariDivision;
import com.GNManagementSystem.GnManagementSystem.entity.UserRole;
import com.GNManagementSystem.GnManagementSystem.enums.Role;
import com.GNManagementSystem.GnManagementSystem.exception.ServiceException;
import com.GNManagementSystem.GnManagementSystem.repository.GramaNiladhariDivisionRepository;
import com.GNManagementSystem.GnManagementSystem.repository.GramaNildhariRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class CitizenConverter {
    private final PasswordEncoder passwordEncoder;
    private final GramaNiladhariDivisionRepository gramaNiladhariDivisionRepository;
    private final GramaNildhariRepository gramaNildhariRepository;
    public Citizen convert(CitizenDto citizenDto){

        GramaNiladhari gramaNiladhari= gramaNildhariRepository.findById(citizenDto.getGramaNiladhariId()).orElseThrow(()->new ServiceException(ApplicationConstants.NOT_FOUND,"GN Division not found", HttpStatus.NOT_FOUND));
        GramaNiladhariDivision gramaNiladhariDivision = gramaNiladhariDivisionRepository.findById(gramaNiladhari.getGramaNiladhariDivision().getId()).orElseThrow(()->new ServiceException("GN Division not found",ApplicationConstants.NOT_FOUND, HttpStatus.NOT_FOUND));
        log.info("________gnd,{}",gramaNiladhariDivision);

        UserRole userRole = new UserRole();
        userRole.setRole(Role.PEOPLE);

        return Citizen.builder()
                .familyCardNo(citizenDto.getFamilyCardNo())
                .child(citizenDto.getChild())
                .mother(citizenDto.getMother())
                .father(citizenDto.getFather())
                .gender(citizenDto.getGender())
                .age((citizenDto.getDateOfBirth() != null) ? Period.between(citizenDto.getDateOfBirth(), LocalDate.now()).getYears() : 0)
                .dateOfBirth(citizenDto.getDateOfBirth())
                .nic(citizenDto.getNic())
                .email(citizenDto.getEmail())
                .firstName(citizenDto.getFirstName())
                .lastName(citizenDto.getLastName())
                .phone(citizenDto.getPhone())
                .city(citizenDto.getCity())
                .password(passwordEncoder.encode(citizenDto.getPassword()))
                .username(citizenDto.getUsername())
                .userRoles(List.of(userRole))
                .gramaNiladhariDivision(gramaNiladhariDivision)
                .build();
    }
}

package com.GNManagementSystem.GnManagementSystem.controller;

import com.GNManagementSystem.GnManagementSystem.agent.CitizenAgent;
import com.GNManagementSystem.GnManagementSystem.dto.CitizenDto;
import com.GNManagementSystem.GnManagementSystem.dto.ResponseDto;
import com.GNManagementSystem.GnManagementSystem.entity.Citizen;
import com.GNManagementSystem.GnManagementSystem.service.CitizenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/citizen")
public class CitizenController {
    private final CitizenAgent citizenAgent;
    private final CitizenService citizenService;
    @PostMapping
    public ResponseDto createCitizen(@RequestBody CitizenDto citizenDto) {
        return citizenAgent.createCitizen(citizenDto);
    }

    @GetMapping
//    @PreAuthorize("hasRole('PEOPLE')")
    public List<Citizen> getAllCitizens() {
        return citizenService.getAllCitizens();
    }

    @GetMapping("/{id}")
    public Citizen getCitizenById(@PathVariable String id) {
        return citizenAgent.getCitizenById(id);
    }
    @GetMapping("by-filter")
    public List<CitizenDto> getCitizenByFilter(@RequestParam(value = "nic",required = false) String nic,
                                               @RequestParam(value = "firstName",required = false) String firstName,
                                               @RequestParam(value = "familyCardNo",required = false) String familyCardNo,
                                               @RequestParam(value = "ageFrom",required = false) Integer ageFrom,
                                               @RequestParam(value = "ageTo",required = false) Integer ageTo,
                                               @RequestParam(value = "gnId",required = false) String gnId) {

        if (ageFrom == null) ageFrom = 0;
        if (ageTo == null) ageTo = 150;
        return citizenAgent.getAllCitizenByFilter(nic,firstName,familyCardNo,ageFrom,ageTo,gnId);
    }


}

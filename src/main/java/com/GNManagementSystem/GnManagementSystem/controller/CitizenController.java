package com.GNManagementSystem.GnManagementSystem.controller;

import com.GNManagementSystem.GnManagementSystem.agent.CitizenAgent;
import com.GNManagementSystem.GnManagementSystem.dto.CitizenDto;
import com.GNManagementSystem.GnManagementSystem.dto.ResponseDto;
import com.GNManagementSystem.GnManagementSystem.entity.Citizen;
import com.GNManagementSystem.GnManagementSystem.service.CitizenService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/citizen")
@Validated
public class CitizenController {
    private final CitizenAgent citizenAgent;
    private final CitizenService citizenService;
    @PostMapping
    public ResponseDto createCitizen(@RequestBody @Valid CitizenDto citizenDto) {
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
                                               @RequestParam(value = "ageFrom",required = false)@NotNull  String ageFrom,
                                               @RequestParam(value = "ageTo",required = false) @NotNull String ageTo,
                                               @RequestParam(value = "gnId",required = false) String gnId) {

        int intAgeFrom = Objects.equals(ageFrom, "") ? 0:Integer.parseInt(ageFrom);
        int intAgeTo = Objects.equals(ageTo, "") ? 100: Integer.parseInt(ageTo);
        return citizenAgent.getAllCitizenByFilter(nic,firstName,familyCardNo,intAgeFrom,intAgeTo,gnId);
    }


}

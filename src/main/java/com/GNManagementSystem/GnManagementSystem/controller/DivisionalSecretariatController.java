package com.GNManagementSystem.GnManagementSystem.controller;

import com.GNManagementSystem.GnManagementSystem.dto.DivisionalSecretariatDto;
import com.GNManagementSystem.GnManagementSystem.dto.DivisionalSecretariatResponseDto;
import com.GNManagementSystem.GnManagementSystem.dto.ResponseDto;
import com.GNManagementSystem.GnManagementSystem.service.DivisionalSecretariatService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/ds")
public class DivisionalSecretariatController {
    private final DivisionalSecretariatService divisionalSecretariatService;
//    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @PostMapping()
    public ResponseDto saveDivisionalSecretariat(@RequestBody DivisionalSecretariatDto divisionalSecretariatDto) {
        return divisionalSecretariatService.saveDivisionalSecretariat(divisionalSecretariatDto);
    }

    @GetMapping
    public List<DivisionalSecretariatResponseDto> getAllDivisionalSecretariats() {
        return divisionalSecretariatService.findAllDivisionalSecretariat();
    }

    @GetMapping("/{id}")
    public DivisionalSecretariatResponseDto getDivisionalSecretariatById(@PathVariable String id) {
        return divisionalSecretariatService.findDivisionalSecretariatById(id);
    }

    @PutMapping
    public ResponseDto updateDivisionalSecretariat(@RequestBody DivisionalSecretariatDto divisionalSecretariatDto) {
        return divisionalSecretariatService.updateDivisionalSecretariat(divisionalSecretariatDto);
    }

    @DeleteMapping("/{id}")
    public ResponseDto deleteDivisionalSecretariat(@PathVariable String id) {
        return divisionalSecretariatService.deleteDivisionalSecretariat(id);
    }
}


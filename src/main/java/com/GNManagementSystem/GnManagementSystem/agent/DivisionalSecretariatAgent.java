package com.GNManagementSystem.GnManagementSystem.agent;

import com.GNManagementSystem.GnManagementSystem.dto.DivisionalSecretariatDto;
import com.GNManagementSystem.GnManagementSystem.dto.DivisionalSecretariatResponseDto;
import com.GNManagementSystem.GnManagementSystem.dto.ResponseDto;
import com.GNManagementSystem.GnManagementSystem.entity.DivisionalSecretariat;
import com.GNManagementSystem.GnManagementSystem.service.DivisionalSecretariatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DivisionalSecretariatAgent {
    private final DivisionalSecretariatService divisionalSecretariatService;
    public ResponseDto saveDivisionalSecretariat(DivisionalSecretariatDto divisionalSecretariatDto) {
        return divisionalSecretariatService.saveDivisionalSecretariat(divisionalSecretariatDto);
    }

    public List<DivisionalSecretariatResponseDto> getAllDivisionalSecretariat() {
        return divisionalSecretariatService.findAllDivisionalSecretariat();
    }

    public DivisionalSecretariatResponseDto getDivisionalSecretariatById(String id) {
        return divisionalSecretariatService.findDivisionalSecretariatById(id);
    }

    public ResponseDto deleteDivisionalSecretariat(String id) {
        return divisionalSecretariatService.deleteDivisionalSecretariat(id);
    }

    public ResponseDto updateDivisionalSecretariat(DivisionalSecretariatDto divisionalSecretariatDto) {
        return updateDivisionalSecretariat(divisionalSecretariatDto);
    }
}

package com.GNManagementSystem.GnManagementSystem.service;

import com.GNManagementSystem.GnManagementSystem.constants.ApplicationConstants;
import com.GNManagementSystem.GnManagementSystem.converter.DivisionalSecretariatConverter;
import com.GNManagementSystem.GnManagementSystem.dto.DivisionalSecretariatDto;
import com.GNManagementSystem.GnManagementSystem.dto.DivisionalSecretariatResponseDto;
import com.GNManagementSystem.GnManagementSystem.dto.ResponseDto;
import com.GNManagementSystem.GnManagementSystem.entity.DivisionalSecretariat;
import com.GNManagementSystem.GnManagementSystem.exception.ServiceException;
import com.GNManagementSystem.GnManagementSystem.repository.DivisionalSecretariatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DivisionalSecretariatService {
    private final DivisionalSecretariatConverter divisionalSecretariatConverter;
    private final DivisionalSecretariatRepository divisionalSecretariatRepository;
    public ResponseDto saveDivisionalSecretariat(DivisionalSecretariatDto divisionalSecretariatDto) {
        DivisionalSecretariat divisionalSecretariat = divisionalSecretariatConverter.convertToDivisionalSecretariat(divisionalSecretariatDto);
        divisionalSecretariatRepository.save(divisionalSecretariat);
        return new ResponseDto("Divisional Secretariat saved successfully");
    }

    public List<DivisionalSecretariatResponseDto> findAllDivisionalSecretariat() {
        List<DivisionalSecretariat> divisionalSecretariats = divisionalSecretariatRepository.findAll();
        return divisionalSecretariats.stream().map(divisionalSecretariatConverter::convertToDivisionalSecretariatResponseDto).toList();
    }

    public DivisionalSecretariatResponseDto findDivisionalSecretariatById(String id) {
        return divisionalSecretariatConverter.convertToDivisionalSecretariatResponseDto(divisionalSecretariatRepository.findById(id).orElseThrow(()->new ServiceException(ApplicationConstants.NOT_FOUND,"ds not found", HttpStatus.NOT_FOUND)));
    }

    public ResponseDto deleteDivisionalSecretariat(String id) {
        divisionalSecretariatRepository.deleteById(id);
        return new ResponseDto("Divisional Secretariat deleted successfully");
    }

    public ResponseDto updateDivisionalSecretariat(DivisionalSecretariatDto divisionalSecretariatDto) {
         divisionalSecretariatRepository.save(divisionalSecretariatConverter.convertToDivisionalSecretariat(divisionalSecretariatDto));
         return new ResponseDto("Divisional Secretariat updated successfully");
    }
}

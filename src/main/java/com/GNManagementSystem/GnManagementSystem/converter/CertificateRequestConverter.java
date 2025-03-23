package com.GNManagementSystem.GnManagementSystem.converter;

import com.GNManagementSystem.GnManagementSystem.constants.ApplicationConstants;
import com.GNManagementSystem.GnManagementSystem.dto.CertificateRequestDto;
import com.GNManagementSystem.GnManagementSystem.dto.CertificateRequestResponseDto;
import com.GNManagementSystem.GnManagementSystem.dto.CertificateStatusUpdateDto;
import com.GNManagementSystem.GnManagementSystem.entity.CertificateRequest;
import com.GNManagementSystem.GnManagementSystem.entity.Citizen;
import com.GNManagementSystem.GnManagementSystem.enums.RequestStatus;
import com.GNManagementSystem.GnManagementSystem.exception.ServiceException;
import com.GNManagementSystem.GnManagementSystem.repository.CitizenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class CertificateRequestConverter {
    private final CitizenRepository citizenRepository;
    public CertificateRequest convertCertificateRequest(CertificateRequestDto certificateRequestDto) {
        Citizen citizen= citizenRepository.findById(certificateRequestDto.getUserId()).orElseThrow(()->new ServiceException(ApplicationConstants.NOT_FOUND,"Citizen not found", HttpStatus.NOT_FOUND));
        return CertificateRequest.builder()
                .typeOfCertificate(certificateRequestDto.getTypeOfCertificate())
                .requestedOrganization(certificateRequestDto.getRequestedOrganization())
                .reason(certificateRequestDto.getReason())
                .status(RequestStatus.PENDING)
                .requestedDate(LocalDate.now())
                .citizen(citizen)
                .build();
    }

    public CertificateRequestResponseDto convertToCertificateRequestResponseDto(CertificateRequest certificateRequest) {
        return CertificateRequestResponseDto.builder()
                .id(certificateRequest.getId())
                .typeOfCertificate(certificateRequest.getTypeOfCertificate())
                .requestedOrganization(certificateRequest.getRequestedOrganization())
                .reason(certificateRequest.getReason())
                .status(certificateRequest.getStatus())
                .citizenId(certificateRequest.getCitizen().getId())
                .requestedDate(certificateRequest.getRequestedDate())
                .requestStatusUpdateDate(certificateRequest.getRequestStatusUpdateDate())
                .build();
    }

    public CertificateRequest convertStatus(CertificateStatusUpdateDto certificateStatusUpdateDto) {
        return CertificateRequest.builder()
                .status(certificateStatusUpdateDto.getCertificateStatus())
                .requestStatusUpdatedBy(certificateStatusUpdateDto.getRequestStatusUpdatedBy())
                .rejectionReason(certificateStatusUpdateDto.getRejectionReason())
                .build();
    }
}

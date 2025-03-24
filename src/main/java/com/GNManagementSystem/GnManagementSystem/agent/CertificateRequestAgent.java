package com.GNManagementSystem.GnManagementSystem.agent;

import com.GNManagementSystem.GnManagementSystem.dto.CertificateRequestDto;
import com.GNManagementSystem.GnManagementSystem.dto.CertificateRequestResponseDto;
import com.GNManagementSystem.GnManagementSystem.dto.RejectCertificateDto;
import com.GNManagementSystem.GnManagementSystem.dto.ResponseDto;
import com.GNManagementSystem.GnManagementSystem.enums.RequestStatus;
import com.GNManagementSystem.GnManagementSystem.enums.TypeOfCertificate;
import com.GNManagementSystem.GnManagementSystem.service.CertificateRequestService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CertificateRequestAgent {
    private final CertificateRequestService certificateRequestService;

    public ResponseDto saveCertificateRequest(CertificateRequestDto certificateRequestDto) {
        return certificateRequestService.save(certificateRequestDto);
    }

    public List<CertificateRequestResponseDto> getAllCertificateRequests() {
        return certificateRequestService.getAllCertificateRequests();
    }
    public List<CertificateRequestResponseDto> getCertificateRequestsByFilter(String userId, TypeOfCertificate typeOfCertificate, RequestStatus requestStatus, LocalDate requestedDate, LocalDate requestedDateTo) {
        return certificateRequestService.getCertificateRequestsByFilter(userId,typeOfCertificate,requestStatus,requestedDate,requestedDateTo);
    }

    public ResponseDto approveCertificateRequest(String id,String updatedById) throws MessagingException {
        return certificateRequestService.approveCertificateRequest(id,updatedById);
    }

    public ResponseDto rejectCertificateRequest(RejectCertificateDto rejectCertificateDto)  {
        return certificateRequestService.rejectCertificateRequest(rejectCertificateDto);
    }
}

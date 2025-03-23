package com.GNManagementSystem.GnManagementSystem.controller;

import com.GNManagementSystem.GnManagementSystem.agent.CertificateRequestAgent;
import com.GNManagementSystem.GnManagementSystem.dto.*;
import com.GNManagementSystem.GnManagementSystem.enums.RequestStatus;
import com.GNManagementSystem.GnManagementSystem.enums.TypeOfCertificate;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/v1/certificate-request")
@RequiredArgsConstructor
public class CertificateRequestController {
    private final CertificateRequestAgent certificateRequestAgent;
    @PostMapping
    public ResponseDto saveCertificateRequest(@RequestBody CertificateRequestDto certificateRequestDto) {
        return certificateRequestAgent.saveCertificateRequest(certificateRequestDto);
    }

    @GetMapping
    public List<CertificateRequestResponseDto> getAllCertificateRequests() {
        return certificateRequestAgent.getAllCertificateRequests();
    }
    @GetMapping("/by-filter")
//    @PreAuthorize("hasRole('GS')")
    public List<CertificateRequestResponseDto> getCertificateRequestsByFilter(@RequestParam(value = "userId",required = false) String userId, @RequestParam(value = "typeOfCertificate",required = false) TypeOfCertificate typeOfCertificate, @RequestParam(value = "requestStatus",required = false) RequestStatus requestStatus, @RequestParam(value = "requestedDate",required = false) LocalDate requestedDate) {
        return certificateRequestAgent.getCertificateRequestsByFilter(userId,typeOfCertificate,requestStatus,requestedDate);
    }

    @GetMapping("/approve")
    public ResponseDto approveCertificateRequest(@RequestBody CertificateApprovalDto certificateApprovalDto) throws MessagingException {
        return certificateRequestAgent.approveCertificateRequest(certificateApprovalDto.getCertificateId(),certificateApprovalDto.getUpdatedById());
    }

    @PostMapping("/reject")
    public ResponseDto rejectCertificateRequest(@RequestBody RejectCertificateDto rejectCertificateDto) {
        certificateRequestAgent.rejectCertificateRequest(rejectCertificateDto);
        return new ResponseDto("Certificate request rejected");
    }


}

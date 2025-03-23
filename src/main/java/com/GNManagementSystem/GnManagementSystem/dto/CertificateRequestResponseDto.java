package com.GNManagementSystem.GnManagementSystem.dto;

import com.GNManagementSystem.GnManagementSystem.enums.RequestStatus;
import com.GNManagementSystem.GnManagementSystem.enums.TypeOfCertificate;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CertificateRequestResponseDto {
    private String id;
    @Enumerated(EnumType.STRING)
    private TypeOfCertificate typeOfCertificate;
    private String reason;
    @Enumerated(EnumType.STRING)
    private String requestedOrganization;
    @Enumerated(EnumType.STRING)
    private RequestStatus status;
    private String rejectionReason;
    private LocalDate requestedDate;
    private LocalDate requestStatusUpdateDate;
    private String requestedBy;
    private String citizenId;
}

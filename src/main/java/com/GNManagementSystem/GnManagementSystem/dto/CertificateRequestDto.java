package com.GNManagementSystem.GnManagementSystem.dto;

import com.GNManagementSystem.GnManagementSystem.enums.TypeOfCertificate;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CertificateRequestDto {
    @Enumerated(EnumType.STRING)
    private TypeOfCertificate typeOfCertificate;
    private String reason;
    private String requestedOrganization;
    private String userId;
}

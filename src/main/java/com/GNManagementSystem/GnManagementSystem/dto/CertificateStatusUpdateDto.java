package com.GNManagementSystem.GnManagementSystem.dto;

import com.GNManagementSystem.GnManagementSystem.enums.RequestStatus;
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
public class CertificateStatusUpdateDto {
    private int certificateId;
    @Enumerated(EnumType.STRING)
    private RequestStatus certificateStatus;
    private String RejectionReason;
    private String requestStatusUpdatedBy;
}

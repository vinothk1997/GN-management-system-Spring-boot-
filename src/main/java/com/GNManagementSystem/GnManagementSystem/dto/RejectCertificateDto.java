package com.GNManagementSystem.GnManagementSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RejectCertificateDto {
    private String certificateId;
    private String rejectReason;
    private String rejectedBy;
}

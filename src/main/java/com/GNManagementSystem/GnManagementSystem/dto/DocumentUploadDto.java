package com.GNManagementSystem.GnManagementSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DocumentUploadDto {
    private String userId;
    private Boolean isSignature;
    private Boolean isCharacterCertificate;
    private Boolean isBirthCertificate;
    private Boolean isNiC;
    private String url;
}

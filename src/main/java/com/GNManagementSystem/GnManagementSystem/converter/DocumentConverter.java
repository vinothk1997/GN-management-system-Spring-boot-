package com.GNManagementSystem.GnManagementSystem.converter;

import com.GNManagementSystem.GnManagementSystem.dto.DocumentUploadDto;
import com.GNManagementSystem.GnManagementSystem.entity.Certificate;
import org.springframework.stereotype.Component;

@Component
public class DocumentConverter {

    public Certificate convertDocument(DocumentUploadDto documentUploadDto) {
        return Certificate.builder()
                .isNic(documentUploadDto.getIsNiC())
                .isBirthCertificate(documentUploadDto.getIsBirthCertificate())
                .isCharacterCertificate(documentUploadDto.getIsCharacterCertificate())
                .url(documentUploadDto.getUrl())
                .build();
    }
}

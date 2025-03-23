package com.GNManagementSystem.GnManagementSystem.agent;

import com.GNManagementSystem.GnManagementSystem.dto.DocumentUploadDto;
import com.GNManagementSystem.GnManagementSystem.dto.ResponseDto;
import com.GNManagementSystem.GnManagementSystem.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DocumentAgent {
    private final DocumentService documentService;

    public ResponseDto saveDocument(DocumentUploadDto documentUploadDto) {
        return documentService.saveDocument(documentUploadDto);
    }

    public String getUrlDocument(String userId, Boolean isCharacterCertificate, Boolean isBirthCertificate, Boolean isNic, Boolean isSignature) {
        return documentService.getUrlDocument(userId,isCharacterCertificate,isBirthCertificate,isNic,isSignature);
    }
}

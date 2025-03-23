package com.GNManagementSystem.GnManagementSystem.service;

import com.GNManagementSystem.GnManagementSystem.converter.DocumentConverter;
import com.GNManagementSystem.GnManagementSystem.dto.DocumentUploadDto;
import com.GNManagementSystem.GnManagementSystem.dto.ResponseDto;
import com.GNManagementSystem.GnManagementSystem.entity.Certificate;
import com.GNManagementSystem.GnManagementSystem.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DocumentService {
    private final DocumentRepository documentRepository;
    private final DocumentConverter documentConverter;

    public ResponseDto saveDocument(DocumentUploadDto documentUploadDto) {
        Certificate certificate = documentConverter.convertDocument(documentUploadDto);
        documentRepository.save(certificate);
        return new ResponseDto("Document saved successfully");
    }

    public String getUrlDocument(String userId, Boolean isCharacterCertificate, Boolean isBirthCertificate, Boolean isNic, Boolean isSignature) {
        return documentRepository.getUrlDocument(userId,isCharacterCertificate,isBirthCertificate,isNic,isSignature);
    }
}

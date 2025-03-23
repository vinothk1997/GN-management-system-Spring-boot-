package com.GNManagementSystem.GnManagementSystem.controller;

import com.GNManagementSystem.GnManagementSystem.agent.DocumentAgent;
import com.GNManagementSystem.GnManagementSystem.dto.DocumentUploadDto;
import com.GNManagementSystem.GnManagementSystem.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/document")
@RequiredArgsConstructor
public class DocumentController {
    private final DocumentAgent documentAgent;
    @PostMapping
    public ResponseDto saveDocument(DocumentUploadDto documentUploadDto) {
        return documentAgent.saveDocument(documentUploadDto);
    }

    @GetMapping()
    public String getDocumentUrl(
            @RequestParam("userId") String userId,
            @RequestParam("isCharacterCertificate") Boolean isCharacterCertificate,
            @RequestParam("isBirthCertificate") Boolean isBirthCertificate,
            @RequestParam("isNic") Boolean isNic,
            @RequestParam("isSignature") Boolean isSignature
            ) {
        return documentAgent.getUrlDocument(userId,isCharacterCertificate,isBirthCertificate,isNic,isSignature);
    }
}

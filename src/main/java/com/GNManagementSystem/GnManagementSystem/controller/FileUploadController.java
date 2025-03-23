package com.GNManagementSystem.GnManagementSystem.controller;

import com.GNManagementSystem.GnManagementSystem.utill.SupabaseStorageService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/storage")
public class FileUploadController {

    private final SupabaseStorageService storageService;
    public FileUploadController(SupabaseStorageService storageService) {
        this.storageService = storageService;
    }
    @PostMapping("/upload")
    @PreAuthorize("hasRole('DS')")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        String fileUrl = storageService.uploadImage(file);
        return ResponseEntity.ok(fileUrl);
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<String> getSignedDownloadUrl(@PathVariable String fileName) {
        return ResponseEntity.ok(storageService.getSignedDownloadUrl(fileName));
    }
}


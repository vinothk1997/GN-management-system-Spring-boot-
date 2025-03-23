package com.GNManagementSystem.GnManagementSystem.controller;

import com.GNManagementSystem.GnManagementSystem.utill.HTMLToPDFService;
import com.GNManagementSystem.GnManagementSystem.utill.SupabaseStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/pdf")
@RequiredArgsConstructor
public class PDFController {

    private final HTMLToPDFService htmlToPDFService;
    private final SupabaseStorageService supabaseStorageService;

    @GetMapping("/download")
    public ResponseEntity<ByteArrayResource> downloadPDF() {
        Map<String, Object> data = new HashMap<>();
        data.put("gsDivision", "Jaffna Town");
        data.put("dsDivision", "Jaffna");
        data.put("date", "22/03/2025");
        data.put("fullName", "Kanagasabai Vinoth");
        data.put("nic", "123456789V");
        data.put("fatherName", "Father Name");
        data.put("motherName", "Mother Name");
        data.put("address", "123, Jaffna, Sri Lanka");
        data.put("village", "Jaffna");
        data.put("years", "10");
        data.put("behavior", "Exemplary");
        data.put("purpose", "Job Application");
        data.put("gnName", "Grama Niladhari Name");

        MultipartFile multipartFile = htmlToPDFService.generatePDFResponse(data);
        String url= supabaseStorageService.uploadImage(multipartFile);

        return null;
    }
}

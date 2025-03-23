package com.GNManagementSystem.GnManagementSystem.utill;

import com.GNManagementSystem.GnManagementSystem.constants.ApplicationConstants;
import com.GNManagementSystem.GnManagementSystem.exception.ServiceException;
import com.itextpdf.html2pdf.HtmlConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.*;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class HTMLToPDFService {
    private final TemplateEngine templateEngine;

    public MultipartFile generatePDFResponse(Map<String, Object> data) {
        Context context = new Context();
        context.setVariables(data);
        String htmlContent = templateEngine.process("character-certificate", context);

        File pdfFile = new File("character_certificate.pdf");

        try (FileOutputStream fos = new FileOutputStream(pdfFile)) {
            HtmlConverter.convertToPdf(htmlContent, fos);
            log.info("PDF generated successfully: {}", pdfFile.getAbsolutePath());

            return convertFileToMultipartFile(pdfFile);

        } catch (IOException e) {
            log.error("Error while generating PDF", e);
        }
        return null;
    }

    public MultipartFile generateIncomeCertificate(Map<String, Object> data) {
        Context context = new Context();
        context.setVariables(data);
        String htmlContent = templateEngine.process("income-certificate", context);
        File pdfFile = new File("income_certificate.pdf");

        try (FileOutputStream fos = new FileOutputStream(pdfFile)) {
            HtmlConverter.convertToPdf(htmlContent, fos);
            log.info("PDF generated successfully: {}", pdfFile.getAbsolutePath());

            return convertFileToMultipartFile(pdfFile);

        } catch (IOException e) {
            log.error("Error while generating PDF", e);
        }
        return null;
    }

    public static MultipartFile convertFileToMultipartFile(File file) throws IOException {
        try (FileInputStream input = new FileInputStream(file)) {
            return new MockMultipartFile("file", file.getName(), "application/pdf", input);
        }
    }
}

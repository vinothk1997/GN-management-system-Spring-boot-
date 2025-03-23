package com.GNManagementSystem.GnManagementSystem.utill;

import com.GNManagementSystem.GnManagementSystem.client.SupabaseFeignClient;
import feign.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class SupabaseStorageService {

    private final SupabaseFeignClient supabaseFeignClient;

    @Value("${supabase.bucket}")
    private String bucketName;

    @Value("${supabase.api.key}")
    private String apiKey;

    @Value("${supabase.public.url}")
    private String publicUrl;


    public String uploadImage(MultipartFile file) {
        try {
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

            Response response = supabaseFeignClient.uploadFile(bucketName, fileName,file.getContentType(),"inline", "Bearer " + apiKey,true, file);
            if (response.status() == 200 || response.status() == 201) {
                return publicUrl + "/" + bucketName + "/" + fileName;
            } else {
                return "Error: " + response.reason();
            }
        } catch (Exception e) {
            return "Error uploading file: " + e.getMessage();
        }
    }
    public String getSignedDownloadUrl(String fileName) {
        Response response = supabaseFeignClient.generateSignedUrl(bucketName, fileName, "Bearer " + apiKey);
        if (response.status() == 200) {
            return response.body().toString(); // Parse response correctly
        } else {
            return "Error: Unable to generate signed URL";
        }
    }

    public String getPublicUrl(String fileName) {
        return publicUrl + "/" + bucketName + "/" + fileName;
    }


}


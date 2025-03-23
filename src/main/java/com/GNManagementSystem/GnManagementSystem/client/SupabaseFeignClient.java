package com.GNManagementSystem.GnManagementSystem.client;

import com.GNManagementSystem.GnManagementSystem.config.FeignConfig;
import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "supabaseClient", url = "${supabase.url}", configuration = FeignConfig.class)
public interface SupabaseFeignClient {
    @PostMapping(value = "/storage/v1/object/{bucket}/{fileName}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Response uploadFile(
            @PathVariable("bucket") String bucket,
            @PathVariable("fileName") String fileName,
            @RequestHeader("Content-Type") String contentType,
            @RequestHeader("Content-Disposition") String contentDisposition,
            @RequestHeader("Authorization") String apiKey,
            @RequestHeader("x-upsert") boolean upsert,
            @RequestPart("file") MultipartFile file
    );

    @PostMapping(value = "/storage/v1/sign/{bucket}/{fileName}")
    Response generateSignedUrl(
            @PathVariable("bucket") String bucket,
            @PathVariable("fileName") String fileName,
            @RequestHeader("Authorization") String apiKey
    );
}

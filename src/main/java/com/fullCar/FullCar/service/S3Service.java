package com.fullCar.FullCar.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.core.sync.RequestBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;


@Service
public class S3Service {

        private static final Logger logger = LoggerFactory.getLogger(S3Service.class);

        private final S3Client s3Client;
        private final String bucketName;

        // Constructor
        public S3Service(S3Client s3Client, @Value("${aws.s3.bucketName}") String bucketName) {
                this.s3Client = s3Client;
                this.bucketName = bucketName;
        }

        // Method
        public String uploadFile(String key, MultipartFile file) {
                try (InputStream is = file.getInputStream()) {
                        // Create RequestBody from InputStream
                        RequestBody requestBody = RequestBody.fromInputStream(is, file.getSize());

                        // Build the request object to send to S3
                        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                                .bucket(bucketName)
                                .key(key)
                                .build();

                        // Upload the file to S3
                        s3Client.putObject(putObjectRequest, requestBody);
                        logger.info("File uploaded successfully to S3. Key: {}", key);

//                         Generate URI
                        return generateFileUrl(key);

                } catch (IOException e) {
                        logger.error("Failed to upload file to S3. Key: {}", key, e);
                        throw new RuntimeException("Failed to upload file.", e);
                }
        }

        private String generateFileUrl(String key){
                try{
                        URI uri = new URI("https://" + bucketName + ".s3.amazonaws.com/" + key);
                        return uri.toString();
                } catch (URISyntaxException e){
                        logger.error("Failed to generate URL for file. Key: {}", key, e);
                        throw new RuntimeException("Failed to generate file URL.", e);
                }
        }
}

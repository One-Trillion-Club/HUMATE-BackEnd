package com.otclub.humate.common.upload;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.otclub.humate.common.exception.CustomException;
import com.otclub.humate.common.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class S3Uploader {
    private final AmazonS3 amazonS3;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public String uploadImage(MultipartFile imageFile) {
        String originName = imageFile.getOriginalFilename();
        String uploadName = changeRandomImageName(originName);
        try {
            uploadToS3(imageFile, uploadName);
        } catch (IOException e) {
            throw new CustomException(ErrorCode.UPLOAD_FAIL);
        }

        return amazonS3.getUrl(bucket, uploadName).toString();
    }

    @Async
    public void uploadToS3(MultipartFile imageFile, String uploadName) throws IOException {
        amazonS3.putObject(new PutObjectRequest(
                bucket,
                uploadName,
                imageFile.getInputStream(),
                createObjectMetadata(imageFile.getSize(), imageFile.getContentType())
                )
        );
    }

    private String changeRandomImageName(String originName) {
        return UUID.randomUUID() + "_" + originName;
    }

    private ObjectMetadata createObjectMetadata(long size, String contentType) {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(contentType);
        metadata.setContentLength(size);
        metadata.setCacheControl("31536000");
        return metadata;
    }
}

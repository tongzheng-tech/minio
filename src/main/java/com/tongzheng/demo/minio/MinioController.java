package com.tongzheng.demo.minio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * MinioController
 *
 * @Author zhengtong
 * @Date 2019/11/7 21:40
 */
@RestController
public class MinioController {

    @Autowired
    MinioService minioService;

    @PostMapping(value = "/minio/file/upload")
    public String upload(@RequestParam String bucketName,
                         @RequestParam String fileName,
                         @RequestParam MultipartFile file) throws IOException {
        return minioService.uploadToMinio(bucketName, fileName, file);
    }
}
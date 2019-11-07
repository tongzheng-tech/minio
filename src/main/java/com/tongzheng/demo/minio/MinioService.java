package com.tongzheng.demo.minio;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * MinioService
 *
 * @Author zhengtong
 * @Date 2019/11/7 21:41
 */
@Service
@Slf4j
public class MinioService {

    @Autowired
    MinioUtil minioUtil;

    public String uploadToMinio(String bucketName, String fileName, MultipartFile file) throws IOException {
        return minioUtil.upload(bucketName, fileName, file.getInputStream());
    }
}

package com.tongzheng.demo.minio;

import io.minio.MinioClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.InputStream;

/**
 * MinioUtil
 *
 * @Author zhengtong
 * @Date 2019/11/7 21:41
 */
@Slf4j
@Component
public class MinioUtil implements InitializingBean {

    @Value("${minio.AccessKey}")
    private String accessKey;

    @Value("${minio.SecretKey}")
    private String secretKey;

    @Value("${minio.BucketName}")
    private String bucketName;

    @Value("${minio.S3Address}")
    private String s3Address;

    private MinioClient minioClient;

    @Override
    public void afterPropertiesSet() throws Exception {
        this.minioClient = new MinioClient(this.s3Address, this.accessKey, this.secretKey);
    }

    public String upload(String bucketName, String fileName, InputStream sourceStream) {
        if (!StringUtils.hasText(bucketName)) {
            bucketName = this.bucketName;
        }
        String fileUrl;
        try {
            // if bucket is not exist, create it!
            if (!minioClient.bucketExists(bucketName)) {
                minioClient.makeBucket(bucketName);
                log.info("[MinioUtil-upload]: create Bucket : " + bucketName);
            }
            fileUrl = minioClient.presignedGetObject(bucketName, fileName);
            // upload
            minioClient.putObject(bucketName, fileName, sourceStream, sourceStream.available(), "UTF-8");
        } catch (Exception e) {
            log.error("[MinioUtil-upload]: upload to minio failed!");
            fileUrl = null;
        }
        return fileUrl;
    }
}

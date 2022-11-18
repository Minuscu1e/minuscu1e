package cn.minuscu1e.file.config;

import io.minio.MinioClient;

public class MinioClientWrapper {

    private MinioClient minioClient;

    private MinioConfigurationProperties properties;

    public MinioClientWrapper(MinioClient minioClient, MinioConfigurationProperties properties) {
        this.minioClient = minioClient;
        this.properties = properties;
    }

    public MinioClient minioClient() {
        return minioClient;
    }

    public String getBucketName() {
        return properties.getBucketName();
    }
}

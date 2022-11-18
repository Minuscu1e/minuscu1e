package cn.minuscu1e.file.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author saling
 */
@Component
@ConfigurationProperties(prefix = "spring.minio")
public class MinioConfigurationProperties {

    private String endPoint;
    private String assessKey;
    private String secretKey;
    private String bucketName;


    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public String getAssessKey() {
        return assessKey;
    }

    public void setAssessKey(String assessKey) {
        this.assessKey = assessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}

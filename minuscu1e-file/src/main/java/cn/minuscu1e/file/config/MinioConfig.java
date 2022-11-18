package cn.minuscu1e.file.config;

import io.minio.MinioClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioConfig {
    private MinioConfigurationProperties properties;

    public MinioConfig(MinioConfigurationProperties properties) {
        this.properties = properties;
    }


    @Bean
    public MinioClientWrapper MinioClientWrapper() {
        MinioClient mc = MinioClient.builder()
                .endpoint(properties.getEndPoint())
                .credentials(properties.getAssessKey(), properties.getSecretKey())
                .build();
        return new MinioClientWrapper(mc, properties);
    }
}

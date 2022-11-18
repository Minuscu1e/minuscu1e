package cn.minuscu1e.common.config;

import cn.minuscu1e.common.exception.GlobalExceptionHandler;
import cn.minuscu1e.common.service.RedisService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class CommonConfiguration {


    /**
     * 统一的异常处理
     *
     * @return
     */
    @Bean
    public GlobalExceptionHandler globalExceptionHandler() {
        return new GlobalExceptionHandler();
    }

    /**
     * redis 模板
     *
     * @param factory
     * @return
     */
    @Bean
    RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setEnableDefaultSerializer(true);
        template.setConnectionFactory(factory);

        return template;
    }

    /**
     * redis 服务
     *
     * @param redis
     * @return
     */
    @Bean
    public RedisService redisService(RedisTemplate<String, Object> redis) {
        return new RedisService(redis);
    }
}

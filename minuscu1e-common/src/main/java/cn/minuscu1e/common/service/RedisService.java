package cn.minuscu1e.common.service;


import org.springframework.data.redis.core.RedisTemplate;

public class RedisService {

    private RedisTemplate<String, Object> redisTemplate;

    public RedisService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void set(final String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public Object get(final String key) {
        return redisTemplate.opsForValue().get(key);
    }
}

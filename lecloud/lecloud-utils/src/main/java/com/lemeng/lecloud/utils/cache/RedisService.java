package com.lemeng.lecloud.utils.cache;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RedisService {

    private RedisTemplate<String, String> redisTemplate;

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public RedisService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void addValue(String key, Object value) throws JsonProcessingException {
        String json = mapper.writeValueAsString(value);
        this.redisTemplate.opsForValue().set(key, json);
    }

    public <T> T getValue(String key, Class<T> clazz) throws JsonProcessingException, IOException {
        String json = this.redisTemplate.opsForValue().get(key);
        return this.mapper.readValue(json, clazz);
    }
}

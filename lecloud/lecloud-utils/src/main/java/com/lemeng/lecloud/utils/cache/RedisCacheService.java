package com.lemeng.lecloud.utils.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisCacheService {

    private final static Logger LOOGER = LoggerFactory.getLogger(RedisCacheService.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    /**
     * 获取redis String 值
     * @param key
     * @return
     */
    public Object getStringValue(String key){
       return stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * 获取redis String 值
     * @param key
     * @return
     */
    public Object getObcjetValue(String key){
        return redisTemplate.opsForValue().get(key);
    }

}

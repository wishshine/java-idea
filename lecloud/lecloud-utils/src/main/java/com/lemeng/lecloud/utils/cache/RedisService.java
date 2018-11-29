package com.lemeng.lecloud.utils.cache;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lemeng.lecloud.model.common.constants.LoginConstants;
import com.lemeng.lecloud.model.common.exception.SystemException;
import com.lemeng.lecloud.model.user.UserLogin;
import com.lemeng.lecloud.utils.common.ObjectPropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

@Component
public class RedisService {

    private final static Logger LOGGER = LoggerFactory.getLogger(RedisService.class);

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

    public UserLogin getUserLogin(String token) {
        try {
            String key = LoginConstants.REDIS_CACHE_LOGIN_TOKEN_KEY + token;
            UserLogin login = this.getValue(key, UserLogin.class);
            Date tokenDate = login.getTokenDate();
            Long time = new Date().getTime();
            Long tokenTime = tokenDate.getTime() + LoginConstants.TOKEN_EFFECTIVE_MILLS;
            if (time > tokenTime) {
                return null;
            }
            return login;
        } catch (Exception e) {
            LOGGER.error("redis获取用户信息错误", e);
            throw new SystemException("redis获取用户信息错误：" + e.getMessage(), e);
        }
    }
}

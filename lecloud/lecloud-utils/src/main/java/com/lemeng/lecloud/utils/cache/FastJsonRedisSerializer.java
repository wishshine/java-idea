package com.lemeng.lecloud.utils.cache;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lemeng.lecloud.model.common.exception.SystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.Charset;

/**
 * redis自定义序列化器
 */
public class FastJsonRedisSerializer<T> implements RedisSerializer<T> {

    private final static Logger LOGGER = LoggerFactory.getLogger(FastJsonRedisSerializer.class);

    private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
    private Class<T> clazz;
    private ObjectMapper mapper = new ObjectMapper();

    public FastJsonRedisSerializer(Class<T> clazz) {
        super();
        this.clazz = clazz;
    }

    @Override
    public byte[] serialize(T t) throws SerializationException {
        if (null != t) {
            try {
                return mapper.writeValueAsString(t).getBytes(DEFAULT_CHARSET);
            } catch (Exception e) {
                LOGGER.error("序列化失败：" + e.getMessage(), e);
                throw new SystemException("序列化失败" + e.getMessage(), e);
            }
        }
        return new byte[0];
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if (null == bytes || bytes.length <= 0) {
            return null;
        }
        String str = new String(bytes, DEFAULT_CHARSET);
        return (T) mapper.convertValue(str, clazz);
    }

}

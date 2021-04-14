package com.wupaas.boot.bussiness.cache;

import com.wupaas.boot.bussiness.context.SpringContextHolder;
import com.wupaas.boot.core.cache.CacheKey;
import com.alibaba.fastjson.JSON;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @author by jueyue on 19-6-30.
 */
public class JsonCacheFactory extends J2CacheFactory {

    private RedisTemplate redisTemplate;

    @Override
    public void put(String cacheName, String key, Object value, long expiresTime) {
        assertRedisTemplate();
        redisTemplate.opsForValue().set(CacheKey.get(cacheName).append(key).toString(), JSON.toJSONString(value), expiresTime, TimeUnit.SECONDS);
    }

    private void assertRedisTemplate() {
        if (redisTemplate == null) {
            redisTemplate = SpringContextHolder.getBean(RedisTemplate.class);
        }
    }


}

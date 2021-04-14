package com.wupaas.boot.core.business.security.shiro.cache;

import net.sf.ehcache.CacheException;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author JueYue
 * @date 2021-03-21-3-11
 * @since 1.0
 */
public class RedisCacheManager implements CacheManager {
    @Autowired
    private RedisTemplate redisTemplate;
    @Value("${spring.redis.expireTime}")
    private long redisExpireTime;

    public RedisCacheManager() {
    }

    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        return new RedisCache(this.redisExpireTime);
    }
}

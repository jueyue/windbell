package cn.afterturn.boot.gateway.config.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author JueYue
 * shiro 缓存管理器
 */
@Component
public class ShiroCacheManager implements CacheManager {

    @Value("${spring.application.name}")
    private String applicationName;

    @Override
    public <K, V> Cache<K, V> getCache(String s) throws CacheException {
        return new ShiroCache<K, V>(applicationName);
    }
}
package com.wupaas.boot.core.business.security.shiro.cache;

/**
 * @author JueYue
 * @date 2021-03-21-3-11
 * @since 1.0
 */

import java.util.Collection;
import java.util.Set;

import com.wupaas.boot.core.business.cache.RedisUtil;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;

public class RedisCache<K, V> implements Cache<K, V> {
    private long expireTime = 3600L;

    public RedisCache() {
    }

    public RedisCache(long expireTime) {
        this.expireTime = expireTime;
    }

    public V get(K key) throws CacheException {
        return RedisUtil.get(key.toString());
    }

    public V put(K key, V value) throws CacheException {
        RedisUtil.put(key.toString(), value, this.expireTime);
        return value;
    }

    public V remove(K key) throws CacheException {
        V v = RedisUtil.get(key.toString());
        RedisUtil.remove(key.toString());
        return v;
    }

    public void clear() throws CacheException {
    }

    public int size() {
        return 0;
    }

    public Set<K> keys() {
        return null;
    }

    public Collection<V> values() {
        return null;
    }
}

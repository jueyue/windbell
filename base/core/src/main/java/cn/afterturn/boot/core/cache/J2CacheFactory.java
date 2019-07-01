package cn.afterturn.boot.core.cache;

import cn.afterturn.boot.core.util.SpringContextHolder;
import net.oschina.j2cache.CacheChannel;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Collection;

/**
 * @author by jueyue on 18-9-17.
 */
public class J2CacheFactory implements ICache {

    private CacheChannel cacheChannel;

    @Override
    public void put(String cacheName, String key, Object value, long expiresTime) {
        throw new NotImplementedException();
    }

    @Override
    public void put(String cacheName, String key, Object value) {
        assertCacheChannel();
        cacheChannel.set(cacheName, key, value);
    }

    @Override
    public <T> T get(String cacheName, String key) {
        assertCacheChannel();
        return (T) cacheChannel.get(cacheName, key, true).getValue();
    }

    @Override
    public Collection<String> getKeys(String cacheName) {
        return cacheChannel.keys(cacheName);
    }

    @Override
    public void remove(String cacheName, String key) {
        cacheChannel.evict(cacheName, key);
    }

    @Override
    public void removeAll(String cacheName) {
        cacheChannel.clear(cacheName);
    }

    @Override
    public boolean exists(String cacheName, String key) {
        return cacheChannel.exists(cacheName, key);
    }

    private void assertCacheChannel() {
        if (cacheChannel == null) {
            cacheChannel = SpringContextHolder.getBean(CacheChannel.class);
        }
    }
}

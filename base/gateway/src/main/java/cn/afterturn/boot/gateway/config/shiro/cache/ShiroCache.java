package cn.afterturn.boot.gateway.config.shiro.cache;

import cn.afterturn.boot.core.cache.CacheKey;
import cn.afterturn.boot.core.cache.RedisKit;
import cn.afterturn.boot.gateway.config.shiro.auth.JwtUtil;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;

import java.util.*;

/**
 * @param <K>
 * @param <V>
 * @author JueYue
 * 重写Shiro的Cache保存读取
 */

public class ShiroCache<K, V> implements Cache<K, V> {

    private String applicationName;

    public ShiroCache(String applicationName) {
        this.applicationName = applicationName;
    }

    /**
     * 获取缓存
     *
     * @param key
     * @return
     * @throws CacheException
     */
    @Override
    public Object get(Object key) throws CacheException {
        String tempKey = this.getKey(key);
        Object result  = null;
        if (RedisKit.exists(tempKey)) {
            result = RedisKit.get(tempKey);
        }
        return result;
    }

    /**
     * 保存缓存
     *
     * @param key
     * @param value
     * @return
     * @throws CacheException
     */
    @Override
    public Object put(Object key, Object value) throws CacheException {
        String tempKey = this.getKey(key);
        RedisKit.put(tempKey, value, 30 * 60);
        return value;
    }

    /**
     * 移除缓存
     *
     * @param key
     * @return
     * @throws CacheException
     */
    @Override
    public Object remove(Object key) throws CacheException {
        String tempKey = this.getKey(key);
        if (RedisKit.exists(tempKey)) {
            RedisKit.remove(tempKey);
        }
        return null;
    }

    @Override
    public void clear() throws CacheException {

    }

    @Override
    public int size() {
        return 20;
    }

    @Override
    public Set<K> keys() {
        Set sets = new HashSet<>();
        //sets.addAll(RedisKit.getKeys(applicationName));
        return sets;
    }

    @Override
    public Collection<V> values() {
        Set     keys   = this.keys();
        List<V> values = new ArrayList<>();
        for (Object key : keys) {
            //values.add((V) RedisKit.get(applicationName, this.getKey(key)));
        }
        return values;
    }

    /**
     * 缓存的key名称获取为shiro:cache:account
     *
     * @param key
     * @return
     */
    private String getKey(Object key) {
        return CacheKey.get(applicationName).append(JwtUtil.getUserId(key.toString())).toString();
    }
}
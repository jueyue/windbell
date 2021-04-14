package com.wupaas.boot.core.business.cache;

import com.wupaas.boot.core.business.context.SpringContextHolder;
import net.oschina.j2cache.CacheChannel;
import org.apache.ibatis.cache.Cache;

import java.util.Collection;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Mybatis 缓存实现
 *
 * @author by jueyue on 18-9-18.
 */
public class J2CacheAdapter implements Cache {

    private static final String DEFAULT_REGION = "default";
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private CacheChannel cache;
    private String id;

    public J2CacheAdapter(String id) {
        if (id == null) {
            id = "default";
        }

        this.id = id;
    }

    public void setId(String id) {
        if (id == null) {
            id = "default";
        }

        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void putObject(Object o, Object o1) {
        getCache().set(this.id, o.toString(), o1);
    }

    @Override
    public Object getObject(Object key) {
        return getCache().get(this.id, key.toString(), new boolean[0]).getValue();
    }

    @Override
    public Object removeObject(Object o) {
        Object obj = getCache().get(this.id, o.toString(), new boolean[0]).getValue();
        if (obj != null) {
            getCache().evict(this.id, new String[]{o.toString()});
        }

        return obj;
    }

    @Override
    public void clear() {
        getCache().clear(this.getId());
    }

    @Override
    public int getSize() {
        Collection<String> keys = getCache().keys(this.getId());
        return keys != null ? keys.size() : 0;
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return this.readWriteLock;
    }

    private CacheChannel getCache() {
        if (cache == null) {
            cache = SpringContextHolder.getBean(CacheChannel.class);
        }
        return cache;
    }

}

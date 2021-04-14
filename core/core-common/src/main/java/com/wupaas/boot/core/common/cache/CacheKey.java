package com.wupaas.boot.core.common.cache;

/**
 * 缓存Key
 *
 * @author by jueyue on 19-6-30.
 */
public final class CacheKey {

    private StringBuilder sb = new StringBuilder();

    public static CacheKey get(String key) {
        CacheKey rk = new CacheKey();
        rk.sb.append(key);
        return rk;
    }

    public CacheKey append(String key) {
        this.sb.append(":");
        this.sb.append(key);
        return this;
    }

    public CacheKey append(int key) {
        this.sb.append(":");
        this.sb.append(key);
        return this;
    }

    public CacheKey append(long key) {
        this.sb.append(":");
        this.sb.append(key);
        return this;
    }

    public CacheKey append(String... keys) {
        for (int i = 0; i < keys.length; i++) {
            this.sb.append(":");
            this.sb.append(keys[i]);
        }
        return this;
    }

    @Override
    public String toString() {
        return this.sb.toString();
    }
}

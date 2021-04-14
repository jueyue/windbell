/**
 * Copyright (c) 2015-2017, Chill Zhuang 庄骞 (smallchill@163.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.wupaas.boot.bussiness.cache;

import com.wupaas.boot.core.cache.ICache;

import java.util.Collection;

/**
 * 缓存工具类
 *
 * @author jueyue
 * @since 1.0 2018-9-17
 */
public class CacheUtil {

    private static ICache defaultCacheFactory = new JsonCacheFactory();


    /**
     * 因为j2cache不支持过期问题,提供redis原生的key提供其他服务
     */
    public static void put(String cacheName, String key, Object value, long expiresTime) {
        defaultCacheFactory.put(cacheName, key, value, expiresTime);
    }

    public static void put(String cacheName, String key, Object value) {
        defaultCacheFactory.put(cacheName, key, value);
    }

    public static <T> T get(String cacheName, String key) {
        return defaultCacheFactory.get(cacheName, key);
    }

    public static Collection<String> getKeys(String cacheName) {
        return defaultCacheFactory.getKeys(cacheName);
    }

    public static void remove(String cacheName, String key) {
        defaultCacheFactory.remove(cacheName, key);
    }

    public static void removeAll(String cacheName) {
        defaultCacheFactory.removeAll(cacheName);
    }

    public static boolean exists(String cacheName, String key) {
        return defaultCacheFactory.exists(cacheName, key);
    }

}



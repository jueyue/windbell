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
package com.wupaas.boot.core.cache;

import java.util.Collection;

/**
 * 通用缓存接口
 *
 * @author jueyue
 */
public interface ICache {

    void put(String cacheName, String key, Object value, long expiresTime);

    void put(String cacheName, String key, Object value);

    <T> T get(String cacheName, String key);

    Collection<String> getKeys(String cacheName);

    void remove(String cacheName, String key);

    void removeAll(String cacheName);

    boolean exists(String cacheName, String key);

}

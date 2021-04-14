package com.wupaas.boot.core.business.context;

import org.springframework.core.env.Environment;

/**
 * 全局环境变量获取yml文件配置
 *
 * @author jueyue on 21-2-19.
 */
public class EnvironmentContextHolder {

    private static Environment environment;

    /**
     * 获取key的值
     *
     * @param key
     * @return
     */
    public static String getVal(String key) {
        Environment env = getEnvironment();
        return env.getProperty(key);
    }

    /**
     * 获取key的值
     *
     * @param key
     * @return
     */
    public static Long getLongVal(String key) {
        Environment env = getEnvironment();
        return env.getProperty(key, Long.class);
    }

    private static Environment getEnvironment() {
        if (environment == null) {
            environment = SpringContextHolder.getBean(Environment.class);
        }
        return environment;
    }
}

package com.wupaas.boot.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 系统管理微服务
 *
 * @author JueYue
 * @since 1.0 2018年7月23日
 */
@EnableCaching
@EnableFeignClients(basePackages = {"com.wupaas.boot.paas.**.clients"})
@EnableConfigurationProperties
@SpringBootApplication(scanBasePackages = "com.wupaas.boot")
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }
}

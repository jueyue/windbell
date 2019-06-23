package cn.afterturn.boot.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 系统管理微服务
 *
 * @author JueYue
 * @since 1.0 2018年7月23日
 */
@EnableCaching
@EnableDiscoveryClient
@EnableConfigurationProperties
@SpringBootApplication(scanBasePackages = "cn.afterturn.boot")
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }
}

package cn.afterturn.boot.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author by jueyue on 18-11-26.
 */
@EnableCaching
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"cn.afterturn.boot.facade.admin"})
@EnableConfigurationProperties
@SpringBootApplication(scanBasePackages = "cn.afterturn.boot")
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
package cn.afterturn.boot.paas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 系统管理微服务
 *
 * @author JueYue
 * @since 1.0 2018年7月23日
 */
@EnableCaching
@EnableDiscoveryClient
@EnableConfigurationProperties
@EnableFeignClients(basePackages = {"cn.afterturn.boot.facade.admin", "cn.afterturn.boot.paas.**.clients"})
@SpringBootApplication(scanBasePackages = "cn.afterturn.boot")
public class PaasApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaasApplication.class, args);
    }
}

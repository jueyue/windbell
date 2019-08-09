package cn.afterturn.boot.wupass;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Created by dbinary on 2019/8/8
 * <p>
 * /
 * /**
 *
 * @author by Administrator on 2019/8/8.
 */
@EnableCaching
@EnableFeignClients
@EnableDiscoveryClient
@EnableConfigurationProperties
@SpringBootApplication(scanBasePackages = "cn.afterturn.boot")
public class WuPassApplication {
    public static void main(String[] args) {
        SpringApplication.run(WuPassApplication.class, args);
    }
}

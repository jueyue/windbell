package cn.afterturn.boot.paas.im.thirdservice.dingding.config;

import cn.afterturn.boot.paas.im.thirdservice.dingding.interceptor.DingTalkAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;

/**
 * 钉钉 feign 配置类
 *
 * @author by jueyue on 19-7-15.
 */
public class DingTalkConfiguration {

    @Bean
    public DingTalkAuthRequestInterceptor dingTalkAuthRequestInterceptor() {
        return new DingTalkAuthRequestInterceptor();
    }
}

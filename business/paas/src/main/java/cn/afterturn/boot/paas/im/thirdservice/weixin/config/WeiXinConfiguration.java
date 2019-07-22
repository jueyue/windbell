package cn.afterturn.boot.paas.im.thirdservice.weixin.config;

import cn.afterturn.boot.paas.im.thirdservice.weixin.interceptor.WeiXinAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;

/**
 * 钉钉 feign 配置类
 *
 * @author by jueyue on 19-7-15.
 */
public class WeiXinConfiguration {

    @Bean
    public WeiXinAuthRequestInterceptor weiXinTalkAuthRequestInterceptor() {
        return new WeiXinAuthRequestInterceptor();
    }
}

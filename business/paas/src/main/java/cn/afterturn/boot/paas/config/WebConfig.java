package cn.afterturn.boot.paas.config;

import cn.afterturn.boot.paas.filter.RequestWrapperFilter;
import cn.afterturn.boot.paas.filter.ServiceContextInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * web配置
 *
 * @author JueYue
 * @date 2017-08-23 15:48
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(serviceContextInterceptor()).addPathPatterns("/**");
    }

    @Bean
    public ServiceContextInterceptor serviceContextInterceptor() {
        return new ServiceContextInterceptor();
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean bean = new FilterRegistrationBean(new RequestWrapperFilter());
        bean.addUrlPatterns("/*");
        return bean;
    }
}

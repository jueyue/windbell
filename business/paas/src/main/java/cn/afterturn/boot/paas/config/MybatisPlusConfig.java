package cn.afterturn.boot.paas.config;

import cn.afterturn.boot.bussiness.intercept.CodeInsertInterceptor;
import cn.afterturn.boot.bussiness.intercept.CodeUpdateInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MybatisPlus配置
 *
 * @author JueYue
 * @Date 2018年8月23日12:51:41
 */
@Configuration
@MapperScan(basePackages = {"cn.afterturn.boot.**.repository"})
public class MybatisPlusConfig {

    /**
     * mybatis-plus分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    @Bean
    public CodeInsertInterceptor codeInsertInterceptor() {
        return new CodeInsertInterceptor();
    }

    @Bean
    public CodeUpdateInterceptor codeUpdateInterceptor() {
        return new CodeUpdateInterceptor();
    }

}

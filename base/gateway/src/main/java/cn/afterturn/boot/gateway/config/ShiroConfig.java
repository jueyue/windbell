package cn.afterturn.boot.gateway.config;

import cn.afterturn.boot.gateway.config.shiro.auth.JwtRealm;
import cn.afterturn.boot.gateway.config.shiro.cache.ShiroCacheManager;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author JueYue
 * 参考 https://www.sundayfine.com/shiro-jwt/
 * shiro 配置
 */
@Configuration
public class ShiroConfig {

    @Bean
    public JwtRealm getJwtRealm() {
        return new JwtRealm();
    }

    @Bean
    public DefaultSecurityManager securityManager(JwtRealm jwtRealm, ShiroCacheManager shiroCacheManager) {
        DefaultSecurityManager securityManager = new DefaultSecurityManager();

        securityManager.setRealm(jwtRealm);

        //关闭shiro自带的session
        DefaultSubjectDAO              subjectDAO                     = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        securityManager.setSubjectDAO(subjectDAO);

        securityManager.setCacheManager(shiroCacheManager);

        SecurityUtils.setSecurityManager(securityManager);
        return securityManager;
    }

}
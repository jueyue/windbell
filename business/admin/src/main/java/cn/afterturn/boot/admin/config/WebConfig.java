package cn.afterturn.boot.admin.config;

import cn.afterturn.boot.admin.config.shiro.DbRealm;
import cn.afterturn.boot.bussiness.auth.JwtRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedList;
import java.util.List;

/**
 * web配置
 *
 * @author JueYue
 * @date 2017-08-23 15:48
 */
@Configuration
public class WebConfig {

    @Autowired(required = false)
    private DbRealm dbRealm;

    @Bean
    public SecurityManager securityManager() {
        List<Realm>               realmList       = new LinkedList<>();
        realmList.add(dbRealm);
        realmList.add(new JwtRealm());
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealms(realmList);

        // 无状态subjectFactory设置
        DefaultSessionStorageEvaluator evaluator = (DefaultSessionStorageEvaluator)((DefaultSubjectDAO) securityManager.getSubjectDAO()).getSessionStorageEvaluator();
        evaluator.setSessionStorageEnabled(Boolean.FALSE);

        SecurityUtils.setSecurityManager(securityManager);
        return securityManager;
    }

}

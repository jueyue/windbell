package com.wupaas.boot.core.business.security.jwt;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * jwt token 代替用户的账户密码信息
 * @author jueyue
 * @date 2021年2月21日
 * @since 1.0.1
 */
public class JwtToken implements AuthenticationToken {

    // 密钥
    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}

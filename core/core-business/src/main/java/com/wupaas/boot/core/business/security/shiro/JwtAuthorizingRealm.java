package com.wupaas.boot.core.business.security.shiro;

import com.wupaas.boot.core.business.security.jwt.JwtToken;
import com.wupaas.boot.core.business.security.jwt.JwtUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * 依赖jwt 上线用户认证
 *
 * @author JueYue
 * @date 2021-02-21-2-21
 * @since 1.0
 */
public class JwtAuthorizingRealm extends AuthorizingRealm {

    @Autowired
    private IShiroPermissionsHandler permissionsHandler;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Set<String> permissions = permissionsHandler.getPermissions(JwtUtil.getUserName(principals.toString()));
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addStringPermissions(permissions);
        return simpleAuthorizationInfo;
    }

    /**
     * 获取用户信息
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        JwtToken jwtToken = (JwtToken) authenticationToken;
        String token = jwtToken.getPrincipal().toString();
        if (JwtUtil.verify(token) == 0) {
            return new SimpleAuthenticationInfo(token, token, JwtUtil.getUserName(token));
        }
        throw new AuthenticationException("token 已经失效");
    }

}

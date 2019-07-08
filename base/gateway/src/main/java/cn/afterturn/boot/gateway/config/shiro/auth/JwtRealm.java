package cn.afterturn.boot.gateway.config.shiro.auth;

import cn.afterturn.boot.bussiness.response.Response;
import cn.afterturn.boot.facade.admin.IResourceFacade;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * jwt 认证服务
 *
 * @author by jueyue on 19-3-30.
 */
public class JwtRealm extends AuthorizingRealm {

    @Autowired
    private IResourceFacade resourceFacade;

    @Override
    public Class<?> getAuthenticationTokenClass() {
        // 此realm只支持jwtToken
        return JwtToken.class;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        String                  token    = (String) principalCollection.getPrimaryPrincipal();
        String                  userId   = JwtUtil.getUserId(token);
        String                  product  = JwtUtil.getProduct(token);
        Response<List<String>>  response = resourceFacade.queryUserPermissions(product, userId);
        SimpleAuthorizationInfo info     = new SimpleAuthorizationInfo();
        if (response.isSuccess()) {
            Set<String> permissions = new HashSet<>();
            for (int i = 0; i < response.getData().size(); i++) {
                permissions.add(getPermission(response.getData().get(i)));
            }
            if (null != permissions && !permissions.isEmpty()) {
                info.setStringPermissions(permissions);
            }
        }
        return info;
    }

    private String getPermission(String url) {
        url = url.replaceAll("/", ":");
        if (url.startsWith(":")) {
            url = url.substring(1);
        }
        if (url.endsWith(":")) {
            url = url.substring(0, url.length() - 1);
        }
        return url;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        if (!(authenticationToken instanceof JwtToken)) {
            return null;
        }
        //JwtCheckGatewayFilterFactory 拦截器已经拦截过了,这里就不拦击了
        JwtToken jwtToken = (JwtToken) authenticationToken;
        return new SimpleAuthenticationInfo(jwtToken.getPrincipal(), jwtToken.getPrincipal(), this.getName());
    }
}
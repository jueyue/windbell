package cn.afterturn.boot.admin.config.shiro;

import cn.afterturn.boot.admin.model.UserModel;
import cn.afterturn.boot.admin.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author by jueyue on 19-4-24.
 */
@Component
public class DbRealm extends AuthorizingRealm {


    @Autowired
    private IUserService userService;

    /**
     * description 此Realm只支持PasswordToken
     *
     * @return java.lang.Class<?>
     */
    @Override
    public Class<?> getAuthenticationTokenClass() {
        return UsernamePasswordToken.class;
    }


    /**
     * description 这里只需要认证登录，成功之后派发 json web token 授权在那里进行
     *
     * @param principalCollection 1
     * @return org.apache.shiro.authz.AuthorizationInfo
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        if (!(authenticationToken instanceof UsernamePasswordToken)) {
            return null;
        }

        if (null == authenticationToken.getPrincipal() || null == authenticationToken.getCredentials()) {
            throw new UnknownAccountException();
        }
        String    account = (String) authenticationToken.getPrincipal();
        UserModel user    = userService.getOne(new QueryWrapper<UserModel>().eq("account", account));
        if (user != null) {
            return new SimpleAuthenticationInfo(account, user.getPassword(), getName());
        } else {
            return new SimpleAuthenticationInfo(account, "", getName());
        }

    }
}

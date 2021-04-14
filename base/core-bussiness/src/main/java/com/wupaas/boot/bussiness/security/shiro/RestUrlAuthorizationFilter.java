package com.wupaas.boot.bussiness.security.shiro;

import com.wupaas.boot.core.exception.BootException;
import com.wupaas.boot.core.exception.BootExceptionEnum;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * url认证拦截器
 *
 * @author jueyue
 * @date 2021年2月21日
 * @since 4.4
 */
public class RestUrlAuthorizationFilter extends PermissionsAuthorizationFilter {
    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {
        //请求的url
        String requestURL = getPathWithinApplication(request);
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            throw new BootException(BootExceptionEnum.NOT_LOGIN);
        }
        //subject.checkPermission(requestURL.replace("/", ":"));
        return true;
    }
}

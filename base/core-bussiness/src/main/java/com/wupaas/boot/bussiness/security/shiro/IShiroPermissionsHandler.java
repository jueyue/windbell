package com.wupaas.boot.bussiness.security.shiro;

import java.util.HashSet;
import java.util.Set;

/**
 * shiro 获取权限的接口服务
 *
 * @author JueYue
 * @date 2021-03-21-3-15
 * @since 1.0
 */
public interface IShiroPermissionsHandler {

    public Set<String> getPermissions(String user);

    default public Set<String> toColon(Set<String> permissions) {
        Set<String> newPermissions = new HashSet<>();
        permissions.forEach(p -> {
            newPermissions.add(p.replace("\\", ":"));
        });
        return newPermissions;
    }

    default public Set<String> getNotNeedPermissions() {
        return null;
    }
}

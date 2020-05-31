package com.wupaas.boot.paas.im.service;

/**
 * 获取token服务
 *
 * @author by jueyue on 19-7-15.
 */
public interface ITokenService {

    /**
     * 获取token
     *
     * @param tenantId
     * @param appType
     * @param appCode
     * @return
     */
    public String getToken(String tenantId, String appType, String appCode);

}

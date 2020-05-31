package com.wupaas.boot.paas.im.thirdservice.dingtalk.interceptor;

import com.wupaas.boot.paas.im.service.ITokenService;
import com.wupaas.boot.paas.common.context.ThirdServiceContext;
import feign.RequestInterceptor;
import feign.RequestTemplate;

import javax.annotation.Resource;

/**
 * @author by jueyue on 19-7-15.
 */
public class DingTalkAuthRequestInterceptor implements RequestInterceptor {

    @Resource(name = "dingTalkTokenService")
    private ITokenService tokenService;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.query("access_token", getToken());
    }

    private String getToken() {
        return ThirdServiceContext.getToken(tokenService);
    }
}

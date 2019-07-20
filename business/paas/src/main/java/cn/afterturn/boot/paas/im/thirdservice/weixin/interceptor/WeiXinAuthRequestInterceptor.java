package cn.afterturn.boot.paas.im.thirdservice.weixin.interceptor;

import cn.afterturn.boot.paas.im.service.ITokenService;
import cn.afterturn.boot.paas.im.thirdservice.ThirdServiceContext;
import feign.RequestInterceptor;
import feign.RequestTemplate;

import javax.annotation.Resource;

/**
 * @author by jueyue on 19-7-15.
 */
public class WeiXinAuthRequestInterceptor implements RequestInterceptor {

    @Resource(name = "weiXinTokenService")
    private ITokenService tokenService;

    @Override
    public void apply(RequestTemplate requestTemplate) {

        requestTemplate.query("access_token", getToken());
    }

    private String getToken() {
        return ThirdServiceContext.getToken(tokenService);
    }
}

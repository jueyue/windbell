package cn.afterturn.boot.paas.im.thirdservice.dingding.interceptor;

import cn.afterturn.boot.paas.im.service.ITokenService;
import cn.afterturn.boot.paas.im.thirdservice.ThirdServiceContext;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 * @author by jueyue on 19-7-15.
 */
public class DingTalkAuthRequestInterceptor implements RequestInterceptor {

    @Autowired
    private ThirdServiceContext thirdServiceContext;

    @Resource(name = "dingTalkTokenService")
    private ITokenService tokenService;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.query("access_token", getToken());
    }

    private String getToken() {
        return thirdServiceContext.getToken(tokenService);
    }
}

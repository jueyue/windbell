package com.wupaas.boot.paas.im.thirdservice.weixin.clients;

import com.wupaas.boot.paas.im.thirdservice.weixin.config.WeiXinConfiguration;
import com.wupaas.boot.paas.im.thirdservice.weixin.model.WeiXinUserInviteModel;
import com.wupaas.boot.paas.im.thirdservice.weixin.model.result.user.WeiXinUserInviteResultModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author by Administrator on 2019/7/19.
 */
@FeignClient(name = "weiXinInviteClient",contextId = "weixin", url = "https://qyapi.weixin.qq.com/cgi-bin/batch",configuration = WeiXinConfiguration.class)
public interface IWeiXinInviteClient {
    /**
     *邀请成员
     * @param model
     *
     * @return
     **/
    @RequestMapping(value = "/invite", method = RequestMethod.POST)
    WeiXinUserInviteResultModel invite(@RequestBody WeiXinUserInviteModel model);
}

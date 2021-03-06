package com.wupaas.boot.paas.im.thirdservice.dingtalk.clients;

import com.wupaas.boot.paas.im.thirdservice.dingtalk.model.result.DingTalkTokenResultModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Token
 *
 * @author by jueyue on 19-7-14.
 */
@FeignClient(name = "dingTalkTokenClient", url = "https://oapi.dingtalk.com/")
public interface IDingTalkTokenClient {

    /**
     * 获取应用的token
     *
     * @param appkey
     * @param appsecret
     * @return
     */
    @RequestMapping(value = "/gettoken", method = RequestMethod.GET)
    DingTalkTokenResultModel getToken(@RequestParam("appkey") String appkey, @RequestParam("appsecret") String appsecret);
}

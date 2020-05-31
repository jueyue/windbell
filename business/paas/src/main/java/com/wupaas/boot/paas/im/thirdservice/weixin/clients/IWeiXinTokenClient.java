package com.wupaas.boot.paas.im.thirdservice.weixin.clients;

import com.wupaas.boot.paas.im.thirdservice.weixin.model.result.WeiXinTokenResultModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author by jueyue on 19-6-30.
 */
@FeignClient(name = "weixinTokenClient", url = "https://qyapi.weixin.qq.com/cgi-bin")
public interface IWeiXinTokenClient {

    /**
     * 获取应用的token
     *
     * @param corpid
     * @param corpsecret
     * @return
     */
    @RequestMapping(value = "/gettoken", method = RequestMethod.GET)
    WeiXinTokenResultModel getToken(@RequestParam("corpid") String corpid, @RequestParam("corpsecret") String corpsecret);

}

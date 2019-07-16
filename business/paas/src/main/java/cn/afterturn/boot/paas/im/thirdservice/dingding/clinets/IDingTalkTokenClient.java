package cn.afterturn.boot.paas.im.thirdservice.dingding.clinets;

import cn.afterturn.boot.paas.im.thirdservice.dingding.model.result.DingTalkTokenResultModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Token
 *
 * @author by jueyue on 19-7-14.
 */
@FeignClient(name = "dingTalkTokenClient", contextId = "dingtalk", url = "https://oapi.dingtalk.com/")
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

package cn.afterturn.boot.paas.im.thirdservice.weixin.clients;

import cn.afterturn.boot.paas.im.thirdservice.weixin.model.result.WeiXinTokenResultModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
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
     * @param corpid
     * @param corpsecret
     * @return
     */
    @RequestMapping(value = "/gettoken", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    WeiXinTokenResultModel getToken(@RequestParam String corpid, @RequestParam String corpsecret);
}

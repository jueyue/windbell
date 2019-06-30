package cn.afterturn.boot.paas.im.thirdservice.weixin.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 企业微信用户管理客户端
 *
 * @author by jueyue on 19-6-30.
 */
@FeignClient(name = "weixinUserClient", url = "https://qyapi.weixin.qq.com/cgi-bin/user")
public interface IWeiXinUserClient {

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"}, consumes = {"application/json;charset=UTF-8"})
    void create(@RequestBody String json);

}

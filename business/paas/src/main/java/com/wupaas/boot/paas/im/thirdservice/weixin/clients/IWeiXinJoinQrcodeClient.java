package com.wupaas.boot.paas.im.thirdservice.weixin.clients;

import com.wupaas.boot.paas.im.thirdservice.weixin.config.WeiXinConfiguration;
import com.wupaas.boot.paas.im.thirdservice.weixin.model.result.user.WeiXinJoinQrcodeResultModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author by dbinary on 2019/7/19.
 */
@FeignClient(name = "weiXinJoinQrcodeClient",contextId = "weixin", url = "https://qyapi.weixin.qq.com/cgi-bin/corp",configuration = WeiXinConfiguration.class)
public interface IWeiXinJoinQrcodeClient {
    /**
     *  *获取加入企业二维码
     * @param sizeType
     *
     * @return WeiXinJoinQrcodeResultModel
     **/
    @RequestMapping(value = "/get_join_qrcode", method = RequestMethod.GET)
    WeiXinJoinQrcodeResultModel getJoinQrcode(@RequestParam("size_type") String sizeType);
}

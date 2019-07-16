package cn.afterturn.boot.paas.im.thirdservice.dingtalk.clinets;

import cn.afterturn.boot.paas.im.thirdservice.dingtalk.config.DingTalkConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 钉钉登录服务
 *
 * @author by jueyue on 19-7-14.
 */
@FeignClient(name = "dingTalkLoginClient", url = "https://oapi.dingtalk.com/", configuration = DingTalkConfiguration.class)
public interface IDingTalkLoginClient {

    /**
     * 认证码登录
     *
     * @param code
     * @href https://open-doc.dingtalk.com/microapp/serverapi2/clotub
     */
    public void authCodeLogin(@RequestParam("code") String code);
}

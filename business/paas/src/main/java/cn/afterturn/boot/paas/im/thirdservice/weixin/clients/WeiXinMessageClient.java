package cn.afterturn.boot.paas.im.thirdservice.weixin.clients;

import cn.afterturn.boot.paas.im.thirdservice.weixin.config.WeiXinConfiguration;
import cn.afterturn.boot.paas.im.thirdservice.weixin.model.WeiXinMessageModel;
import cn.afterturn.boot.paas.im.thirdservice.weixin.model.WeiXinUserModel;
import cn.afterturn.boot.paas.im.thirdservice.weixin.model.result.message.WeiXinMessageResultModel;
import cn.afterturn.boot.paas.im.thirdservice.weixin.model.result.user.WeiXinUserResultModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 发送应用消息
 * Created by dbinary on 2019/7/20
 *@FeignClient
 */
@FeignClient(name = "weiXinMessageClient",contextId = "weixin", url = "https://qyapi.weixin.qq.com/cgi-bin/message",configuration = WeiXinConfiguration.class)
public interface WeiXinMessageClient {

    /**
     *发送文字
     * @param messageModel
     * @return
     */
    @RequestMapping(value = "/send", method = RequestMethod.POST)
    WeiXinMessageResultModel sendText(@RequestBody WeiXinMessageModel messageModel );
    /**
     *发送图片
     * @param messageModel
     * @return
     */
    @RequestMapping(value = "/send", method = RequestMethod.POST)
    WeiXinMessageResultModel sendPicture(@RequestBody WeiXinMessageModel messageModel );
    /**
     *发送声音
     * @param messageModel
     * @return
     */
    @RequestMapping(value = "/send", method = RequestMethod.POST)
    WeiXinMessageResultModel sendVoice(@RequestBody WeiXinMessageModel messageModel );
    /**
     *发送视频
     * @param messageModel
     * @return
     */
    @RequestMapping(value = "/send", method = RequestMethod.POST)
    WeiXinMessageResultModel sendVeido(@RequestBody WeiXinMessageModel messageModel );
    /**
     *发送文本卡片
     * @param messageModel
     * @return
     */
    @RequestMapping(value = "/send", method = RequestMethod.POST)
    WeiXinMessageResultModel sendTextcard(@RequestBody WeiXinMessageModel messageModel );
}

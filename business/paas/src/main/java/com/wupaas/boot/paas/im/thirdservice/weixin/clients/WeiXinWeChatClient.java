package com.wupaas.boot.paas.im.thirdservice.weixin.clients;

import com.wupaas.boot.paas.im.thirdservice.weixin.config.WeiXinConfiguration;
import com.wupaas.boot.paas.im.thirdservice.weixin.model.*;
import com.wupaas.boot.paas.im.thirdservice.weixin.model.result.WeXinBaseResultModel;
import com.wupaas.boot.paas.im.thirdservice.weixin.model.result.message.WeiXinMessageResultModel;
import com.wupaas.boot.paas.im.thirdservice.weixin.model.result.wechat.WeiXinWeChatInfoResultModel;
import com.wupaas.boot.paas.im.thirdservice.weixin.model.result.wechat.WeiXinWeChatResultModel;
import com.wupaas.boot.paas.im.thirdservice.weixin.model.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * 群聊会话
 * Created by dbinary on 2019/7/20
 *@FeignClient
 */
@FeignClient(name = "weiXinWeChatClient",contextId = "weixin", url = "https://qyapi.weixin.qq.com/cgi-bin/appchat",configuration = WeiXinConfiguration.class)
public interface WeiXinWeChatClient {

    /**
     *创建群聊会话
     * @param messageModel
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    WeiXinWeChatResultModel create(@RequestBody WeiXinWeChatInfoCreateModel messageModel);
    /**
     *修改群聊会话
     * @param messageModel
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    WeXinBaseResultModel update(@RequestBody WeiXinWeChatUpdateModel messageModel);
    /**
     *获取群聊会话
     * @param chatid
     * @return
     */
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    WeiXinWeChatInfoResultModel get(@RequestParam("chatid") String chatid);


    /**
     *发送文字文本消息
     * @param messageModel
     * @return
     */
    @RequestMapping(value = "/send", method = RequestMethod.POST)
    WeXinBaseResultModel sendText(@RequestBody WeiXinWeChatMessageTextModel messageModel);
    /**------------------------------------
     *发送图片
     * @param messageModel
     * @return WeiXinMessageResultModel
     */
    @RequestMapping(value = "/send", method = RequestMethod.POST)
    WeiXinMessageResultModel sendPicture(@RequestBody WeiXinWeChatMessagePictureModel messageModel);
    /**
     *发送声音
     * @param messageModel
     * @return WeiXinMessageResultModel
     */
    @RequestMapping(value = "/send", method = RequestMethod.POST)
    WeiXinMessageResultModel sendVoice(@RequestBody WeiXinWeChatMessageVoiceModel messageModel);
    /**
     *发送视频
     * @param messageModel
     * @return WeiXinMessageResultModel
     */
    @RequestMapping(value = "/send", method = RequestMethod.POST)
    WeiXinMessageResultModel sendVeido(@RequestBody WeiXinWeChatMessageVideoModel messageModel);
    /**
     *发送file
     * @param messageModel
     * @return WeiXinMessageResultModel
     */
    @RequestMapping(value = "/send", method = RequestMethod.POST)
    WeiXinMessageResultModel sendFile(@RequestBody WeiXinWeChatMessageFileModel messageModel);
    /**
     *发送文本卡片
     * @param messageModel
     * @return WeiXinMessageResultModel
     */
    @RequestMapping(value = "/send", method = RequestMethod.POST)
    WeiXinMessageResultModel sendTextcard(@RequestBody WeiXinWeChatMessageTextCardModel messageModel);
    /**
     *发送图文消息
     * @return WeiXinMessageResultModel
     * @return
     */
    @RequestMapping(value = "/send", method = RequestMethod.POST)
    WeiXinMessageResultModel sendNews(@RequestBody WeiXinWeChatMessageNewsModel messageModel);
    /**
     *发送图文消息mpnews
     * @return WeiXinMessageResultModel
     * @return
     */
    @RequestMapping(value = "/send", method = RequestMethod.POST)
    WeiXinMessageResultModel sendMpNews(@RequestBody WeiXinWeChatMessageMpNewsModel messageModel);
    /**
     *发送markdown消息
     * @param messageModel
     * @return WeiXinMessageResultModel
     */
    @RequestMapping(value = "/send", method = RequestMethod.POST)
    WeiXinMessageResultModel sendMarkdown(@RequestBody WeiXinWeChatMessageMarkDownModel messageModel);

}

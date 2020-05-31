package com.wupaas.boot.paas.im.thirdservice.weixin.clients;

import com.wupaas.boot.paas.im.thirdservice.weixin.config.WeiXinConfiguration;
import com.wupaas.boot.paas.im.thirdservice.weixin.model.*;
import com.wupaas.boot.paas.im.thirdservice.weixin.model.result.message.WeiXinMessageResultModel;
import com.wupaas.boot.paas.im.thirdservice.weixin.model.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by dbinary on 2019/7/23
 * <p>
 * /互联企业发送消息
 * /**
 *
 */
@FeignClient(name = "weiXinLinkCorpClient",contextId = "weixin", url = "https://qyapi.weixin.qq.com/cgi-bin/linkedcorp/message",configuration = WeiXinConfiguration.class)
public interface IWeiXinLinkCorpClient {
    /**
     *发送文字
     * @param messageModel
     * @return
     */
    @RequestMapping(value = "/send", method = RequestMethod.POST)
    WeiXinMessageResultModel sendText(@RequestBody WeiXinMessageLinkedcorpTextCardModel messageModel);
    /**
     *发送图片
     * @param messageModel
     * @return WeiXinMessageResultModel
     */
    @RequestMapping(value = "/send", method = RequestMethod.POST)
    WeiXinMessageResultModel sendPicture(@RequestBody WeiXinMessageLinkedcorpPictureModel messageModel);
    /**
     *发送声音
     * @param messageModel
     * @return WeiXinMessageResultModel
     */
    @RequestMapping(value = "/send", method = RequestMethod.POST)
    WeiXinMessageResultModel sendVoice(@RequestBody WeiXinMessageLinkedcorpVoiceModel messageModel);
    /**
     *发送文件
     * @param messageModel
     * @return WeiXinMessageResultModel
     */
    @RequestMapping(value = "/send", method = RequestMethod.POST)
    WeiXinMessageResultModel sendFile(@RequestBody WeiXinMessageLinkedcorpFileModel messageModel);
    /**
     *发送视频
     * @param messageModel
     * @return WeiXinMessageResultModel
     */
    @RequestMapping(value = "/send", method = RequestMethod.POST)
    WeiXinMessageResultModel sendVeido(@RequestBody WeiXinMessageLinkedcorpVideoModel messageModel);
    /**
     *发送文本卡片
     * @param messageModel
     * @return WeiXinMessageResultModel
     */
    @RequestMapping(value = "/send", method = RequestMethod.POST)
    WeiXinMessageResultModel sendTextcard(@RequestBody WeiXinMessageLinkedcorpTextCardModel messageModel);
    /**
     *发送图文消息
     * @return WeiXinMessageResultModel
     * @return
     */
    @RequestMapping(value = "/send", method = RequestMethod.POST)
    WeiXinMessageResultModel sendNews(@RequestBody WeiXinMessageLinkedcorpNewsModel messageModel);
    /**
     *发送图文消息mpnews
     * @return WeiXinMessageResultModel
     * @return
     */
    @RequestMapping(value = "/send", method = RequestMethod.POST)
    WeiXinMessageResultModel sendMpNews(@RequestBody WeiXinMessageLinkedcorpMpNewsModel messageModel);
    /**
     *发送图文消息mpnews
     * @param messageModel
     * @return WeiXinMessageResultModel
     */
    @RequestMapping(value = "/send", method = RequestMethod.POST)
    WeiXinMessageResultModel sendMarkdown(@RequestBody WeiXinMessageLinkedcorpMarkDownModel messageModel);
    /**
     *小程序通知消息
     * @param messageModel
     * @return WeiXinMessageResultModel
     */
    @RequestMapping(value = "/send", method = RequestMethod.POST)
    WeiXinMessageResultModel sendMniprogramNotice(@RequestBody WeiXinMessageLinkedcorpMiniprogramModel messageModel);

}

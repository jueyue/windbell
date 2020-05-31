package com.wupaas.boot.paas.im.thirdservice.weixin.clients;

import com.wupaas.boot.paas.im.thirdservice.weixin.config.WeiXinConfiguration;
import com.wupaas.boot.paas.im.thirdservice.weixin.model.*;
import com.wupaas.boot.paas.im.thirdservice.weixin.model.result.message.WeiXinMessageResultModel;
import com.wupaas.boot.paas.im.thirdservice.weixin.model.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

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
    WeiXinMessageResultModel sendText(@RequestBody WeiXinMessageTextModel messageModel);
    /**
     *发送图片
     * @param messageModel
     * @return WeiXinMessageResultModel
     */
    @RequestMapping(value = "/send", method = RequestMethod.POST)
    WeiXinMessageResultModel sendPicture(@RequestBody WeiXinMessagePictureModel messageModel);
    /**
     *发送声音
     * @param messageModel
     * @return WeiXinMessageResultModel
     */
    @RequestMapping(value = "/send", method = RequestMethod.POST)
    WeiXinMessageResultModel sendVoice(@RequestBody WeiXinMessageVoiceModel messageModel);
    /**
     *发送文件
     * @param messageModel
     * @return WeiXinMessageResultModel
     */
    @RequestMapping(value = "/send", method = RequestMethod.POST)
    WeiXinMessageResultModel sendFile(@RequestBody WeiXinMessageFileModel messageModel);
    /**
     *发送视频
     * @param messageModel
     * @return WeiXinMessageResultModel
     */
    @RequestMapping(value = "/send", method = RequestMethod.POST)
    WeiXinMessageResultModel sendVeido(@RequestBody WeiXinMessageVedioModel messageModel);
    /**
     *发送文本卡片
     * @param messageModel
     * @return WeiXinMessageResultModel
     */
    @RequestMapping(value = "/send", method = RequestMethod.POST)
    WeiXinMessageResultModel sendTextcard(@RequestBody WeiXinMessageTextCardModel messageModel);
    /**
     *发送图文消息
     * @return WeiXinMessageResultModel
     * @return
     */
    @RequestMapping(value = "/send", method = RequestMethod.POST)
    WeiXinMessageResultModel sendNews(@RequestBody WeiXinMessageNewsModel messageModel);
    /**
     *发送图文消息mpnews
     * @return WeiXinMessageResultModel
     * @return
     */
    @RequestMapping(value = "/send", method = RequestMethod.POST)
    WeiXinMessageResultModel sendMpNews(@RequestBody WeiXinMessageMpnewsModel messageModel);
    /**
     *发送图文消息mpnews
     * @param messageModel
     * @return WeiXinMessageResultModel
     */
    @RequestMapping(value = "/send", method = RequestMethod.POST)
    WeiXinMessageResultModel sendMarkdown(@RequestBody WeiXinMessageMarkdownModel messageModel);
    /**
     *小程序通知消息
     * @param messageModel
     * @return WeiXinMessageResultModel
     */
    @RequestMapping(value = "/send", method = RequestMethod.POST)
    WeiXinMessageResultModel sendMniprogramNotice(@RequestBody WeiXinMessageMniprogramNoticeModel messageModel);
    /**
     *任务卡片消息
     * @param messageModel
     * @return WeiXinMessageResultModel
     */
    @RequestMapping(value = "/send", method = RequestMethod.POST)
    WeiXinMessageResultModel sendTaskcard(@RequestBody WeiXinMessageTaskCardModel messageModel);
    /**
     *更新任务卡片消息状态
     * @param sendJson
     * @return WeiXinMessageResultModel
     * {
     *     "userids" : ["userid1","userid2"],
     *     "agentid" : 1,
     *     "task_id": "taskid122",
     *     "clicked_key": "btn_key123"
     * }
     */
    @RequestMapping(value = "/update_taskcard", method = RequestMethod.POST)
    WeiXinMessageResultModel updateTaskcard(@RequestBody Map<String,Object> sendJson);
}

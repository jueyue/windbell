package cn.afterturn.boot.paas.im.thirdservice.weixin.model;

import lombok.Data;

import java.util.Map;

/**
 * Created by dbinary on 2019/7/22
 * <p>
 * /群聊发送
 * /**
 *
 */
@Data
public class WeiXinWeChatMessageVoiceModel extends  WeiXinWeChatMessageModel{
    /**
     * 消息内容，最长不超过2048个字节
     */
    private Map<String,Object> voice;
}

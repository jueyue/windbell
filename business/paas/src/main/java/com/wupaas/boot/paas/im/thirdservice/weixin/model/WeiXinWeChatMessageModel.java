package com.wupaas.boot.paas.im.thirdservice.weixin.model;

import lombok.Data;

/**
 * Created by dbinary on 2019/7/20
 * <p>
 */
@Data
public class WeiXinWeChatMessageModel {

    /**
     *消息类型，w文本发送此时固定为：text
     */
    private String msgtype;
    /**
     *群聊id
     */
    private int chatid;

    /**
     *表示是否是保密消息，0表示否，1表示是，默认0
     */
    private int safe;

}

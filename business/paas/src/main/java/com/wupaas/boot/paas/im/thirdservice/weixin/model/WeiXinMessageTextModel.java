package com.wupaas.boot.paas.im.thirdservice.weixin.model;

import lombok.Data;

import java.util.Map;

/**
 * @author by dabinary on 2019/7/21.
 */
@Data
public class WeiXinMessageTextModel extends  WeiXinMessageModel{
    /**
     *消息内容，最长不超过2048个字节，超过将截断---文字
     */
    private Map<String,Object> text;
}

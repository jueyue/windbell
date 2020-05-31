package com.wupaas.boot.paas.im.thirdservice.weixin.model;
/**
 * @author dbinary 2019/7/21.
 */

import lombok.Data;

import java.util.Map;

@Data
public class WeiXinMessagePictureModel extends WeiXinMessageModel{
    /**
     *消息内容，最长不超过2048个字节，超过将截断 --图片
     */
    private Map<String,Object> image;
}

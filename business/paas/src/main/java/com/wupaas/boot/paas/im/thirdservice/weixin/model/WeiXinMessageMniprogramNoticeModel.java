package com.wupaas.boot.paas.im.thirdservice.weixin.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Map;

/**
 * Created by dbinary on 2019/7/22
 * <p>
 * /
 * /**小程序通知消息
 *
 */
@Data
public class WeiXinMessageMniprogramNoticeModel extends  WeiXinMessageModel {
    @JSONField(name = "miniprogram_notice")
    private Map<String,Object> miniprogramNotice;
}

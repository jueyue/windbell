package com.wupaas.boot.paas.im.thirdservice.weixin.model;

import lombok.Data;

import java.util.Map;

/**
 * Created by dbinary on 2019/7/22
 * <p>
 * /文本卡片消息
 */
@Data
public class WeiXinMessageTextCardModel extends WeiXinMessageModel{
    /**
     *     textcard" : {
     *             "title" : "领奖通知",
     *             "description" : "<div class=\"gray\">2016年9月26日</div> <div class=\"normal\">恭喜你抽中iPhone 7一台，领奖码：xxxx</div><div class=\"highlight\">请于2016年10月10日前联系行政同事领取</div>",
     *             "url" : "URL",
     *             "btntxt":"更多"
     * }
     */
    private Map<String,Object> textcard;
}

package com.wupaas.boot.paas.im.thirdservice.weixin.model.result.oa;

import lombok.Data;

/**
 * Created by dbinary on 2019/7/24
 * <p>
 * /
 * /**
 *WiFi打卡地点信息
 */
@Data
public class WifimacInfo {
    /**
     * WiFi打卡地点名称
     */
    private String wifiname;
    /**
     * WiFi打卡地点MAC地址/bssid
     */
    private String wifimac;
}

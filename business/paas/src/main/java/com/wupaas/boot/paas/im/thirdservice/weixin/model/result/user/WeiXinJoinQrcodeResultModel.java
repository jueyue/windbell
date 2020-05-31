package com.wupaas.boot.paas.im.thirdservice.weixin.model.result.user;

import com.wupaas.boot.paas.im.thirdservice.weixin.model.result.WeXinBaseResultModel;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author by dbinary on 2019/7/19.
 */
@Data
public class WeiXinJoinQrcodeResultModel extends WeXinBaseResultModel {
    /**
     *二维码链接，有效期7天
     */
    @JSONField(name = "join_qrcode")
    private String joinQrcode;
}

package com.wupaas.boot.paas.im.thirdservice.dingtalk.model.result;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author by jueyue on 19-7-14.
 */
@Data
public class DingTalkTokenResultModel extends DingTalkBaseResultModel {

    @JSONField(name = "access_token")
    private String accessToken;
}

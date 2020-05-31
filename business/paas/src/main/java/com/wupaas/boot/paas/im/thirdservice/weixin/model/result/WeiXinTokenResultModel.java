package com.wupaas.boot.paas.im.thirdservice.weixin.model.result;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 微信 token调用返回对象
 *
 * @author by dbinary on 19-6-30.
 */
@Data
public class WeiXinTokenResultModel extends WeXinBaseResultModel {

    /**
     * 获取到的凭证，最长为512字节
     */

    @JSONField(name = "access_token")
    private String accessToken;
    /**
     * 凭证的有效时间（秒）
     */
    private long expiresIn;
}

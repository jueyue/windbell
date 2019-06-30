package cn.afterturn.boot.paas.im.thirdservice.weixin.model.result;

import cn.afterturn.boot.paas.im.thirdservice.weixin.model.WeXinBaseResultModel;
import lombok.Data;

/**
 * 微信 token调用返回对象
 *
 * @author by jueyue on 19-6-30.
 */
@Data
public class WeiXinTokenResultModel extends WeXinBaseResultModel {

    /**
     * 获取到的凭证，最长为512字节
     */
    private String accessToken;
    /**
     * 凭证的有效时间（秒）
     */
    private long expiresIn;
}

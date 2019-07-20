package cn.afterturn.boot.paas.im.thirdservice.weixin.model.result.user;

import cn.afterturn.boot.paas.im.thirdservice.weixin.model.result.WeXinBaseResultModel;
import lombok.Data;

/**
 * @author by dbinary on 2019/7/19.
 */
@Data
public class WeiXinUserOpenIdUserIdResultModel extends WeXinBaseResultModel {
    /**
     * 企业内的成员id
     */
    private String userid;
    /**
     * 在使用企业支付之后，返回结果的openid
     */
    private String openid;
}

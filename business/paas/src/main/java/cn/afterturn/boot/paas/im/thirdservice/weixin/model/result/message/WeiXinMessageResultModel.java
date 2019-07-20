package cn.afterturn.boot.paas.im.thirdservice.weixin.model.result.message;

import cn.afterturn.boot.paas.im.thirdservice.weixin.model.result.WeXinBaseResultModel;
import lombok.Data;

/**
 * Created by dbinary on 2019/7/20
 * <p>
 * /
 */
@Data
public class WeiXinMessageResultModel extends WeXinBaseResultModel {
    /**
     *无效的用户
     */
    private String invaliduser;
    /**
     *无效的部门
     */
    private String invalidparty;
    /**
     *无效的标签
     */
    private String invalidtag;
}

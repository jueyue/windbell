package com.wupaas.boot.paas.im.thirdservice.weixin.model.result.user;

import com.wupaas.boot.paas.im.thirdservice.weixin.model.result.WeXinBaseResultModel;
import lombok.Data;

/**
 * @author by dbinary on 2019/7/19.
 */
@Data
public class WeiXinUserInviteResultModel extends WeXinBaseResultModel {
    /**
     * 	非法成员列表
     */
    private String invaliduser;
    /**
     * 非法部门列表
     */
   private String  invalidparty;
    /**
     * 非法标签列表
     */
   private String  invalidtag;
}

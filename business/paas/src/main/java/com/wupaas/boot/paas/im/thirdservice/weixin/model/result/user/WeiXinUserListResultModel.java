package com.wupaas.boot.paas.im.thirdservice.weixin.model.result.user;

import com.wupaas.boot.paas.im.thirdservice.weixin.model.result.WeXinBaseResultModel;
import lombok.Data;

import java.util.List;

/**
 * @author by dbinary on 2019/7/19.
 */
@Data
public class WeiXinUserListResultModel extends WeXinBaseResultModel {
    /**
     *成员列表
     **/
    private List<WeiXinUserResultModel> userlist;

}

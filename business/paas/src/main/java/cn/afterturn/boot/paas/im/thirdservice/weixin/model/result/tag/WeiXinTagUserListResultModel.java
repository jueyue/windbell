package cn.afterturn.boot.paas.im.thirdservice.weixin.model.result.tag;

import cn.afterturn.boot.paas.im.thirdservice.weixin.model.result.WeXinBaseResultModel;

import java.util.List;

/**
 * Created by dbinary on 2019/7/20
 *
 */
public class WeiXinTagUserListResultModel extends WeXinBaseResultModel {
    /**
     * 非法的成员帐号列表
     */
    private String invalidlist;
    /**
     * 非法的部门id列表
     */
    private List<Integer> invalidparty;
}

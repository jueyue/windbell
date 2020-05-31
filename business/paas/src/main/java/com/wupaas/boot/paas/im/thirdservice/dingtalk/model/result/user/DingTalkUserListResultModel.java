package com.wupaas.boot.paas.im.thirdservice.dingtalk.model.result.user;

import com.wupaas.boot.paas.im.thirdservice.dingtalk.model.DingTalkUserModel;
import com.wupaas.boot.paas.im.thirdservice.dingtalk.model.result.DingTalkBaseResultModel;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;

/**
 * @author by jueyue on 19-7-16.
 */
@Data
public class DingTalkUserListResultModel extends DingTalkBaseResultModel {

    /**
     *在分页查询时返回，代表是否还有下一页更多数据
     **/
    private String hasMore;

    /**
     *成员列表
     **/
    private List<DingTalkUserModel> userlist;
    /**
     * 管理员列表成员列表
     * "sys_level":2,"userid":"userid2"
     **/
    @JSONField(name = "admin_list")
    private List<DingTalkUserModel> adminList;
}

package com.wupaas.boot.paas.im.thirdservice.dingtalk.model.result.role;

import com.wupaas.boot.paas.im.thirdservice.dingtalk.model.DingTalkUserModel;
import lombok.Data;

import java.util.List;

/**
 * @author by jueyue on 19-7-18.
 */
@Data
public class DingTalkRoleUserResultModel {

    /**
     * 是否还有更多数据
     **/
    private boolean hasMore;

    /**
     * 下次拉取数据的游标
     **/
    private long nextCursor;

    /**
     * 用户
     * "userid":"manager7978",
     *  "name":"小钉"
     */
    private List<DingTalkUserModel> list;
}

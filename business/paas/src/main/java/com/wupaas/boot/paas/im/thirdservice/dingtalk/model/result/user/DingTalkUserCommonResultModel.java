package com.wupaas.boot.paas.im.thirdservice.dingtalk.model.result.user;

import com.wupaas.boot.paas.im.thirdservice.dingtalk.model.result.DingTalkBaseResultModel;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;

/**
 * @author by jueyue on 19-7-16.
 */
@Data
public class DingTalkUserCommonResultModel extends DingTalkBaseResultModel {

    /**
     * 企业员工数量
     */
    private int count;

    @JSONField(name = "dept_ids")
    private List<Integer> deptIds;
}

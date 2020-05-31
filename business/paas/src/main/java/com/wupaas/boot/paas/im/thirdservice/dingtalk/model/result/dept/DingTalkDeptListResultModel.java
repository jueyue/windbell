package com.wupaas.boot.paas.im.thirdservice.dingtalk.model.result.dept;

import com.wupaas.boot.paas.im.thirdservice.dingtalk.model.DingTalkDeptModel;
import com.wupaas.boot.paas.im.thirdservice.dingtalk.model.result.DingTalkBaseResultModel;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;

/**
 * @author by jueyue on 19-7-17.
 */
@Data
public class DingTalkDeptListResultModel extends DingTalkBaseResultModel {

    /**
     * 子部门数据
     */
    @JSONField(name = "sub_dept_id_list")
    private List<String> subDeptIdList;

    /**
     * 部门列表
     */
    private List<DingTalkDeptModel> department;

    /**
     * 该部门的所有父部门id列表
     */
    private List<String> parentIds;

}

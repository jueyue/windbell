package com.wupaas.boot.paas.im.thirdservice.weixin.model.result.dept;

import com.wupaas.boot.paas.im.thirdservice.weixin.model.result.WeXinBaseResultModel;
import lombok.Data;

import java.util.List;

/**
 * @author by Administrator on 2019/7/19.
 */
@Data
public class WeiXinDeptListResultModel extends WeXinBaseResultModel {
    /**
     *   部门列表数据。
     */

    private List<WeiXinDeptResultModel> department;
}

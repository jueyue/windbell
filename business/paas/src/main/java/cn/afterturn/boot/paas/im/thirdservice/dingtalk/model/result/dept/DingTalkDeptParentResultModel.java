package cn.afterturn.boot.paas.im.thirdservice.dingtalk.model.result.dept;

import cn.afterturn.boot.paas.im.thirdservice.dingtalk.model.result.DingTalkBaseResultModel;
import lombok.Data;

import java.util.List;

/**
 * @author by jueyue on 19-7-17.
 */
@Data
public class DingTalkDeptParentResultModel extends DingTalkBaseResultModel {

    /**
     * 该部门的所有父部门id列表
     */
    private List<List<String>> department;
}

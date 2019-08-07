package cn.afterturn.boot.paas.im.thirdservice.dingtalk.model.result.role;

import cn.afterturn.boot.paas.im.thirdservice.dingtalk.model.result.DingTalkBaseResultModel;
import lombok.Data;

/**
 * @author by jueyue on 19-7-18.
 */
@Data
public class DingTalkRoleResultModel extends DingTalkBaseResultModel {
    /**
     * 角色组id
     **/
    private long roleId;
    /**
     * 角色组id
     **/
    private long groupId;

}

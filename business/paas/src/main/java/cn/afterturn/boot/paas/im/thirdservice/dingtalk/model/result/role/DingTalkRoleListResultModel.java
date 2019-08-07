package cn.afterturn.boot.paas.im.thirdservice.dingtalk.model.result.role;

import cn.afterturn.boot.paas.im.thirdservice.dingtalk.model.DingTalkRoleGroupModel;
import cn.afterturn.boot.paas.im.thirdservice.dingtalk.model.result.DingTalkBaseResultModel;
import lombok.Data;

import java.util.List;

/**
 * @author by jueyue on 19-7-18.
 */
@Data
public class DingTalkRoleListResultModel extends DingTalkBaseResultModel {

    private String result;

    @Data
    public static class DingTalkRoleListModel {

        private boolean hasMore;

        private List<DingTalkRoleGroupModel> list;
    }
}

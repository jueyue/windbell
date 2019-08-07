package cn.afterturn.boot.paas.im.thirdservice.dingtalk.model;

import lombok.Data;

import java.util.List;

/**
 * 角色组
 *
 * @author by jueyue on 19-7-18.
 */
@Data
public class DingTalkRoleGroupModel {

    private String name;

    private String groupId;

    private List<DingTalkRoleModel> roles;
}

package cn.afterturn.boot.paas.im.thirdservice.dingtalk.model.result.user;

import cn.afterturn.boot.paas.im.thirdservice.dingtalk.model.result.DingTalkBaseResultModel;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;

/**
 * @author by jueyue on 19-7-16.
 */
@Data
public class DingTalkUserResultModel extends DingTalkBaseResultModel {

    /**
     * 管理员角色，1:主管理员，2:子管理员
     **/
    @JSONField(name = "sys_level")
    private String sysLevel;
    /**
     * 员工唯一标识
     **/
    private String userid;
    /**
     * 联系类型，0表示企业内部员工，1表示企业外部联系人
     **/
    private String contactType;
    /**
     * 员工在当前开发者企业账号范围内的唯一标识，系统生成，固定值，不会改变
     **/
    private String unionid;

    /**
     * 员工名字
     **/
    private String name;

    /**
     * 分机号（仅限企业内部开发调用）
     **/
    private String tel;

    /**
     * 办公地点
     **/
    private String workPlace;

    /**
     * 备注
     **/
    private String remark;

    /**
     * 手机号码
     **/
    private String mobile;

    /**
     * 员工的电子邮箱
     **/
    private String email;

    /**
     * 员工的企业邮箱，如果员工已经开通了企业邮箱，接口会返回，否则不会返回
     **/
    private String orgEmail;

    /**
     * 是否已经激活，true表示已激活，false表示未激活
     **/
    private String active;

    /**
     * 在对应的部门中的排序，Map结构的json字符串，key是部门的Id，value是人员在这个部门的排序值
     **/
    private String orderInDepts;

    /**
     * 是否为企业的管理员，true表示是，false表示不是
     **/
    private String isAdmin;

    /**
     * 是否为企业的老板，true表示是，false表示不是
     **/
    private String isBoss;

    /**
     * 在对应的部门中是否为主管：Map结构的json字符串，key是部门的Id，value是人员在这个部门中是否为主管，true表示是，false表示不是
     **/
    private String isLeaderInDepts;

    /**
     * 是否号码隐藏，true表示隐藏，false表示不隐藏
     **/
    private String isHide;

    /**
     * 成员所属部门id列表
     **/
    private String department;

    /**
     * 职位信息
     **/
    private String position;

    /**
     * 头像url
     **/
    private String avatar;

    /**
     * 入职时间。Unix时间戳 （在OA后台通讯录中的员工基础信息中维护过入职时间才会返回)
     **/
    private String hiredDate;

    /**
     * 员工工号
     **/
    private String jobnumber;

    /**
     * 扩展属性，可以设置多种属性（但手机上最多只能显示10个扩展属性，具体显示哪些属性，请到OA管理后台->设置->通讯录信息设置和OA管理后台->设置->手机端显示信息设置）
     **/
    private String extattr;

    /**
     * 是否是高管
     **/
    private String isSenior;

    /**
     * 国家地区码
     **/
    private String stateCode;

    /**
     * 用户所在角色列表
     **/
    private List roles;

    /**
     * userid列表
     */
    private List<String> userIds;

}

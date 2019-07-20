package cn.afterturn.boot.paas.im.thirdservice.weixin.model.result.user;


import cn.afterturn.boot.paas.im.thirdservice.weixin.model.result.WeXinBaseResultModel;
import lombok.Data;

import java.util.List;

/**
 * @author by dbinary on 2019/7/19.
 */
@Data
public class WeiXinUserResultModel extends WeXinBaseResultModel {
    /**
     * 员工在当前企业内的唯一标识，也称staffId。可由企业在创建时指定，并代表一定含义比如工号，创建后不可修改，企业内必须唯一。 长度为1~64个字符，如果不传，服务器将自动生成一个userid。
     **/
    private String userid;

    /**
     * 成员名称。 长度为1~64个字符
     **/
    private String name;


    /**
     * 数组类型，数组里面值为整型，成员所属部门id列表
     **/
    private List department;

    /**
     * 职位信息。 长度为0~64个字符
     **/
    private String position;

    /**
     * 手机号码，企业内必须唯一，不可重复。如果是国际号码，请使用+xx-xxxxxx的格式
     **/
    private String mobile;

    /**
     * 座机。32字节以内，由纯数字或’-‘号组成。
     **/
    private String tellephone;


    /**
     * 备注，长度为0~1000个字符
     **/
    private String remark;

    /**
     * 邮箱。长度为0~64个字符。企业内必须唯一，不可重复
     **/
    private String email;
    /**
     *
     * 性别。1表示男性，2表示女性
     */
    private String gender;

    private List userlist;
}

package cn.afterturn.boot.facade.paas.im.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author by jueyue on 19-6-30.
 */
@Data
public class PaasUserRequestModel extends PaasBaseRequestModel {

    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("账号")
    private String account;

    @ApiModelProperty("名字")
    private String name;

    @ApiModelProperty("生日")
    private Date birthday;

    @ApiModelProperty("性别")
    private Integer sex;

    @ApiModelProperty("电子邮件")
    private String email;

    @ApiModelProperty("电话")
    private String phone;

    @ApiModelProperty("部门ID")
    private String deptId;

    @ApiModelProperty("状态(1：启用 2 停用 3 删除)")
    private Integer status;
}

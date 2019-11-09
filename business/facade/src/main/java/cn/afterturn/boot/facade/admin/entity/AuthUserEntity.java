package cn.afterturn.boot.facade.admin.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * auth user entity
 *
 * @author jueyue on 19-11-9.
 */
@Data
@ApiModel
public class AuthUserEntity {

    @ApiModelProperty("租户")
    private String tenantId;

    @ApiModelProperty("用户ID")
    private String userId;

    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("账号")
    private String account;

    @ApiModelProperty("密码,原始秘钥或者32位MD5")
    private String password;

    @ApiModelProperty("名字")
    private String name;

    @ApiModelProperty("类型")
    private Integer type;
}

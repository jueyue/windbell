/**
 * Copyright 2017-2019 JueYue (qrb.jueyue@foxmail.com)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.wupaas.boot.admin.model;

import com.wupaas.boot.bussiness.model.IdTenantBaseModel;
import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户秘钥
 *
 * @author JueYue
 * @Date 2019-11-09 14:01:51
 */
@Data
@ApiModel("用户秘钥")
@TableName(value = "t_system_user_auth", resultMap = UserAuthModel.BASE_RESULT_MAP)
public class UserAuthModel extends IdTenantBaseModel<UserAuthModel> {

    private static final long serialVersionUID = 1L;


    @Excel(name = "用户ID")
    @TableField(value = "USER_ID", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty(value = "用户ID", required = true)
    private String userId;

    @Excel(name = "头像")
    @TableField(value = "AVATAR", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("头像")
    private String avatar;

    @Excel(name = "账号")
    @TableField(value = "ACCOUNT", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("账号")
    private String account;

    @Excel(name = "密码")
    @TableField(value = "PASSWORD", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("密码")
    private String password;

    @Excel(name = "MD5密码盐")
    @TableField(value = "SALT", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("MD5密码盐")
    private String salt;

    @Excel(name = "名字")
    @TableField(value = "NAME", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("名字")
    private String name;

    @Excel(name = "类型")
    @TableField(value = "TYPE")
    @ApiModelProperty(value = "类型", required = true)
    private Integer type;

    @Excel(name = "状态")
    @TableField(value = "STATUS")
    @ApiModelProperty("状态")
    private Integer status;
}

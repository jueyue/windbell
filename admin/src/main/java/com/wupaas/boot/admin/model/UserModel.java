/**
 * Copyright 2017-2018 JueYue (qrb.jueyue@foxmail.com)
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

import com.wupaas.boot.core.business.model.IdTenantBaseModel;
import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 用户管理
 *
 * @author JueYue
 * @Date 2018-09-06 20:36:08
 */
@Data
@ApiModel("用户管理")
@TableName(value = "t_system_user", resultMap = UserModel.BASE_RESULT_MAP)
public class UserModel extends IdTenantBaseModel<UserModel> {

    private static final long serialVersionUID = 1L;


    @Excel(name = "头像")
    @TableField(value = "AVATAR", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("头像")
    private String avatar;

    @Excel(name = "账号")
    @TableField(value = "ACCOUNT", condition = SqlCondition.LIKE, strategy = FieldStrategy.NOT_EMPTY)
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
    @TableField(value = "NAME", condition = SqlCondition.LIKE, strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("名字")
    private String name;

    @Excel(name = "生日")
    @TableField(value = "BIRTHDAY")
    @ApiModelProperty("生日")
    private Date birthday;

    @Excel(name = "性别")
    @TableField(value = "SEX")
    @ApiModelProperty("性别")
    private Integer sex;

    @Excel(name = "电子邮件")
    @TableField(value = "EMAIL", condition = SqlCondition.LIKE, strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("电子邮件")
    private String email;

    @Excel(name = "电话")
    @TableField(value = "PHONE", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("电话")
    private String phone;

    @Excel(name = "部门ID")
    @TableField(value = "DEPT_ID", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("部门ID")
    private String deptId;

    @Excel(name = "状态(1：启用 2 停用 3 删除)")
    @TableField(value = "STATUS")
    @ApiModelProperty("状态(1：启用 2 停用 3 删除)")
    private Integer status;

    @Excel(name = "1是管理员")
    @TableField(value = "IS_ADMIN")
    @ApiModelProperty("1是管理员")
    private Integer isAdmin;

    @Excel(name = "钉钉ID")
    @TableField(value = "DDID", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("钉钉ID")
    private String ddid;

    @Excel(name = "微信ID")
    @TableField(value = "WXID", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("微信ID")
    private String wxid;

    @TableField(exist = false)
    private String access;
}

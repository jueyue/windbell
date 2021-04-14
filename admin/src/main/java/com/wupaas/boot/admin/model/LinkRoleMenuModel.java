/**
 * Copyright 2017-2018 JueYue (qrb.jueyue@foxmail.com)
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.wupaas.boot.admin.model;

import com.wupaas.boot.core.business.model.IdBaseModel;
import com.baomidou.mybatisplus.annotation.*;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import cn.afterturn.easypoi.excel.annotation.Excel;

/**
 * 角色和菜单关联表
 *
 * @author JueYue
 * @Date 2019-06-02 22:08:04
 */
@Data
@ApiModel("角色和菜单关联表")
@TableName(value = "t_system_link_role_menu", resultMap = LinkRoleMenuModel .BASE_RESULT_MAP)
public class LinkRoleMenuModel extends IdBaseModel<LinkRoleMenuModel> {

    private static final long serialVersionUID = 1L;

    public LinkRoleMenuModel(){}
    public LinkRoleMenuModel(String menuId,String roleId){
        this.menuId = menuId;
        this.roleId = roleId;
    }

    @Excel(name = "菜单ID")
    @TableField(value="MENU_ID" , strategy = FieldStrategy.NOT_EMPTY )
    @ApiModelProperty("菜单ID")
    private String menuId;

    @Excel(name = "角色ID")
    @TableField(value="ROLE_ID" , strategy = FieldStrategy.NOT_EMPTY )
    @ApiModelProperty("角色ID")
    private String roleId;

}

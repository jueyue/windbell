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

import com.wupaas.boot.bussiness.model.IdBaseModel;
import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 资源和菜单关联表
 *
 * @author JueYue
 * @Date 2019-07-08 11:15:12
 */
@Data
@ApiModel("资源和菜单关联表")
@TableName(value = "t_system_link_menu_resource", resultMap = LinkMenuResourceModel.BASE_RESULT_MAP)
public class LinkMenuResourceModel extends IdBaseModel<LinkMenuResourceModel> {

    private static final long serialVersionUID = 1L;

    @Excel(name = "菜单ID")
    @TableField(value = "MENU_ID", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("菜单ID")
    private String menuId;

    @Excel(name = "资源ID")
    @TableField(value = "RESOURCE_ID", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("资源ID")
    private String resourceId;

    public LinkMenuResourceModel(String resourceId, String menuId) {
        this.resourceId = resourceId;
        this.menuId = menuId;
    }
}

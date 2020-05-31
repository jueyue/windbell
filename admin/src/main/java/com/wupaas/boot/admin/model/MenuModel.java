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

import com.wupaas.boot.bussiness.model.IdBaseModel;
import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 菜单表
 *
 * @author JueYue
 * @Date 2018-10-12 14:51:42
 */
@Data
@ApiModel("菜单表")
@TableName(value = "t_system_menu", resultMap = MenuModel.BASE_RESULT_MAP)
public class MenuModel extends IdBaseModel<MenuModel> {

    private static final long serialVersionUID = 1L;

    @Excel(name = "显示类型 1 PC 后台 2 钉钉||微信 3小程序 4公众号")
    @TableField(value = "WEB_TYPE")
    @ApiModelProperty("显示类型 1 PC 后台 2 钉钉||微信 3小程序 4公众号")
    private Integer webType;

    @Excel(name = "菜单编号")
    @TableField(value = "CODE", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("菜单编号")
    private String code;

    @Excel(name = "菜单父编号")
    @TableField(value = "P_ID", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("菜单父编号")
    private String pid;

    @Excel(name = "菜单名称")
    @TableField(value = "NAME", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("菜单名称")
    private String name;

    @Excel(name = "菜单图标")
    @TableField(value = "ICON", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("菜单图标")
    private String icon;

    @Excel(name = "URL地址")
    @TableField(value = "URL", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("URL地址")
    private String url;

    @Excel(name = "菜单排序号")
    @TableField(value = "NUM")
    @ApiModelProperty("菜单排序号")
    private Integer num;

    @Excel(name = "菜单层级")
    @TableField(value = "LEVELS")
    @ApiModelProperty("菜单层级")
    private Integer levels;

    @Excel(name = "是否是菜单（1：是  0：不是）")
    @TableField(value = "ISMENU")
    @ApiModelProperty("是否是菜单（1：是  0：不是）")
    private String ismenu;

    @Excel(name = "备注")
    @TableField(value = "TIPS", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("备注")
    private String tips;

    @Excel(name = "归属产品")
    @TableField(value = "PRO_CODE", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("归属产品")
    private String proCode;

    @Excel(name = "菜单状态")
    @TableField(value = "STATUS")
    @ApiModelProperty("菜单状态 :  1:启用   0:不启用")
    private String status;

    @Excel(name = "是否打开")
    @TableField(value = "ISOPEN")
    @ApiModelProperty("是否打开:    1:打开   0:不打开")
    private String isopen;

    @ApiModelProperty("子菜单")
    @TableField(exist = false)
    private List<MenuModel> children;

    /**
     * 是不是直接生成基础资源:新增,修改,查询,删除,批量删除
     * 1 生成 其他不生成
     */
    @TableField(exist = false)
    private Integer base;
}

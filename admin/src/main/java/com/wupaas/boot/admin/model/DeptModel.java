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

import com.wupaas.boot.bussiness.model.IdTenantBaseModel;
import cn.afterturn.easypoi.excel.annotation.Excel;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 部门表
 *
 * @author JueYue
 * @Date 2018-09-18 15:25:41
 */
@Data
@ApiModel("部门表")
@TableName(value = "t_system_dept", resultMap = DeptModel.BASE_RESULT_MAP)
public class DeptModel extends IdTenantBaseModel<DeptModel> {

    private static final long serialVersionUID = 1L;


    @Excel(name = "排序")
    @TableField(value = "NUM")
    @ApiModelProperty("排序")
    private Integer num;

    @Excel(name = "父部门ID")
    @TableField(value = "P_ID", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("父部门ID")
    @JSONField(serializeUsing = ToStringSerializer.class)
    private String pid;

    @Excel(name = "简称")
    @TableField(value = "SIMPLE_NAME", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("简称")
    private String simpleName;

    @Excel(name = "全称")
    @TableField(value = "FULL_NAME", strategy = FieldStrategy.NOT_EMPTY, condition = SqlCondition.LIKE)
    @ApiModelProperty("全称")
    private String fullName;

    @Excel(name = "部门编码")
    @TableField(value = "DEPT_CODE", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("部门编码")
    private String deptCode;

    @Excel(name = "提示")
    @TableField(value = "TIPS", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("提示")
    private String tips;

    @Excel(name = "版本")
    @TableField(value = "VERSION")
    @ApiModelProperty("版本")
    private Integer version;

    @Excel(name = "微信ID")
    @TableField(value = "WXID", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("微信ID")
    private String wxid;

    @Excel(name = "钉钉ID")
    @TableField(value = "DDID", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("钉钉ID")
    private String ddid;

    @ApiModelProperty("子部门")
    @TableField(exist = false)
    private List<DeptModel> children;

}

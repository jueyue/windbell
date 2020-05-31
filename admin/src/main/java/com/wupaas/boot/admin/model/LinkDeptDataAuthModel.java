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

/**
 * 部门数据权限表关联表
 *
 * @author JueYue
 * @Date 2018-09-18 16:52:18
 */
@Data
@ApiModel("部门数据权限表关联表")
@TableName(value = "t_system_link_dept_data_auth", resultMap = LinkDeptDataAuthModel.BASE_RESULT_MAP)
public class LinkDeptDataAuthModel extends IdBaseModel<LinkDeptDataAuthModel> {

    private static final long serialVersionUID = 1L;

    public LinkDeptDataAuthModel() {
    }

    public LinkDeptDataAuthModel(String deptId, String dataDeptId, Integer type) {
        this.dataDeptId = dataDeptId;
        this.deptId = deptId;
        this.type = type;

    }

    @Excel(name = "部门ID")
    @TableField(value = "DEPT_ID", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("部门ID")
    private String deptId;

    @Excel(name = "数据部门ID")
    @TableField(value = "DATA_DEPT_ID", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("数据部门ID")
    private String dataDeptId;

    @Excel(name = "类型 1 包含下级 2 不包含下级")
    @TableField(value = "TYPE")
    @ApiModelProperty("类型 1 包含下级 2 不包含下级")
    private Integer type;
}

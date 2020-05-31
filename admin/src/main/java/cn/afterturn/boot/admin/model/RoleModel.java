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
package cn.afterturn.boot.admin.model;

import cn.afterturn.boot.bussiness.model.IdTenantBaseModel;
import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 角色表
 *
 * @author JueYue
 * @Date 2018-09-05 16:35:32
 */
@Data
@ApiModel("角色表")
@TableName(value = "t_system_role", resultMap = RoleModel.BASE_RESULT_MAP)
public class RoleModel extends IdTenantBaseModel<RoleModel> {

    private static final long serialVersionUID = 1L;


    @Excel(name = "序号")
    @TableField(value = "NUM")
    @ApiModelProperty("序号")
    private Integer num;

    @Excel(name = "父角色ID")
    @TableField(value = "P_ID", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("父角色ID")
    private String pid;

    @Excel(name = "角色名称")
    @TableField(value = "NAME", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("角色名称")
    private String name;

    @Excel(name = "部门名称")
    @TableField(value = "DEPT_ID", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("部门名称")
    private String deptId;

    @Excel(name = "提示")
    @TableField(value = "TIPS", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("提示")
    private String tips;

    @Excel(name = "保留字段(暂时没用）")
    @TableField(value = "VERSION")
    @ApiModelProperty("保留字段(暂时没用）")
    private Integer version;

    @Excel(name = "微信ID")
    @TableField(value = "WXID", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("微信ID")
    private String wxid;

    @Excel(name = "钉钉ID")
    @TableField(value = "DDID", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("钉钉ID")
    private String ddid;



}

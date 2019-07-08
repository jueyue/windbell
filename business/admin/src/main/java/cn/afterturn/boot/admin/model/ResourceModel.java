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
package cn.afterturn.boot.admin.model;

import cn.afterturn.boot.bussiness.model.IdBaseModel;
import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 资源管理
 *
 * @author JueYue
 * @Date 2019-07-06 17:09:00
 */
@Data
@ApiModel("资源管理")
@TableName(value = "t_system_resource", resultMap = ResourceModel.BASE_RESULT_MAP)
public class ResourceModel extends IdBaseModel<ResourceModel> {

    private static final long serialVersionUID = 1L;


    @Excel(name = "资源父编号")
    @TableField(value = "P_ID", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("资源父编号")
    private String pid;

    @Excel(name = "资源名称")
    @TableField(value = "NAME", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("资源名称")
    private String name;

    @Excel(name = "URL地址")
    @TableField(value = "URL", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("URL地址")
    private String url;

    @Excel(name = "子系统")
    @TableField(value = "SUB_SYSTEM", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("子系统")
    private String subSystem;

    @Excel(name = "排序号")
    @TableField(value = "NUM")
    @ApiModelProperty("排序号")
    private Integer num;

    @Excel(name = "类型")
    @TableField(value = "TYPE")
    @ApiModelProperty("类型")
    private Integer type;

    @Excel(name = "备注")
    @TableField(value = "TIPS", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("备注")
    private String tips;

    @Excel(name = "状态")
    @TableField(value = "STATUS")
    @ApiModelProperty("状态")
    private Integer status;

}

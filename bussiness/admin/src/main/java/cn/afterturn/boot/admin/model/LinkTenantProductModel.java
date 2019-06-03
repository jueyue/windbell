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

import cn.afterturn.boot.bussiness.model.IdBaseModel;
import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 租户开通产品信息
 *
 * @author JueYue
 * @Date 2018-09-18 15:34:41
 */
@Data
@ApiModel("租户开通产品信息")
@TableName(value = "t_system_link_tenant_product", resultMap = LinkTenantProductModel.BASE_RESULT_MAP)
public class LinkTenantProductModel extends IdBaseModel<LinkTenantProductModel> {

    private static final long serialVersionUID = 1L;


    @Excel(name = "产品代码")
    @TableField(value = "PRO_CODE", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("产品代码")
    private String proCode;

    @Excel(name = "机构编号")
    @TableField(value = "TENANT_ID", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("机构编号")
    private String tenantId;

    @Excel(name = "开始时间")
    @TableField(value = "START_TIME")
    @ApiModelProperty("开始时间")
    private Date startTime;

    @Excel(name = "结束时间")
    @TableField(value = "END_TIME")
    @ApiModelProperty("结束时间")
    private Date endTime;

    @Excel(name = "状态 1 有效 2 临时停用 3 注销")
    @TableField(value = "STATUS")
    @ApiModelProperty("状态 1 有效 2 临时停用 3 注销")
    private Integer status;

}

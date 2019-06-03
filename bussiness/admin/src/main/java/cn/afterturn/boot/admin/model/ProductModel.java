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

import cn.afterturn.boot.bussiness.model.BaseModel;
import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 产品配置
 *
 * @author JueYue
 * @Date 2018-09-05 16:40:10
 */
@Data
@ApiModel("产品配置")
@TableName(value = "t_system_product", resultMap = ProductModel.BASE_RESULT_MAP)
public class ProductModel extends BaseModel<ProductModel> {

    private static final long serialVersionUID = 1L;

    @TableField(exist = false)
    private int id;

    @Excel(name = "产品代码")
    @TableId(value = "PRO_CODE")
    @ApiModelProperty("产品代码")
    private String proCode;

    @Excel(name = "产品类型")
    @TableField(value = "PRO_TYPE", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("产品类型")
    private String proType;

    @Excel(name = "产品名称")
    @TableField(value = "PRO_NAME", strategy = FieldStrategy.NOT_EMPTY, condition = SqlCondition.LIKE)
    @ApiModelProperty("产品名称")
    private String proName;

    @Excel(name = "状态：1.正常使用中2.临时停用3.注销")
    @TableField(value = "STATUS", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("状态：1.正常使用中2.临时停用3.注销")
    private String status;

    @Excel(name = "备注")
    @TableField(value = "MEMO", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("备注")
    private String memo;

    @Override
    protected Serializable pkVal() {
        return proCode;
    }
}

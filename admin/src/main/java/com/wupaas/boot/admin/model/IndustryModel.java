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

import com.wupaas.boot.core.business.model.IdBaseModel;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 行业信息表
 *
 * @author JueYue
 * @Date 2018-11-12 15:52:05
 */
@Data
@ApiModel("行业信息表")
@TableName(value = "t_base_industry")
public class IndustryModel extends IdBaseModel<IndustryModel> {

    private static final long serialVersionUID = 1L;

    @TableField(value = "NAME", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("所属行业名称")
    private String name;

    @TableField(value = "PARENT_ID")
    @ApiModelProperty("上级")
    private Integer parentId;
}

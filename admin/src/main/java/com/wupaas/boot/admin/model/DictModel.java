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
import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 字典表
 *
 * @author JueYue
 * @Date 2018-09-06 20:58:18
 */
@Data
@ApiModel("字典表")
@TableName(value = "t_system_dict", resultMap = DictModel.BASE_RESULT_MAP)
public class DictModel extends IdBaseModel<DictModel> {

    private static final long serialVersionUID = 1L;


    @Excel(name = "KEY")
    @TableField(value = "DICT_KEY", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("KEY")
    private String key;

    @Excel(name = "父级字典")
    @TableField(value = "P_ID", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("父级字典")
    private String pid;

    @Excel(name = "父KEY")
    @TableField(value = "PKEY", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("父KEY")
    private String pkey;

    @Excel(name = "名称")
    @TableField(value = "NAME", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("名称")
    private String name;

    @Excel(name = "提示")
    @TableField(value = "TIPS", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("提示")
    private String tips;

    @TableField(exist = false)
    @ApiModelProperty("字典值表")
    private List<DictModel> subDict;

}

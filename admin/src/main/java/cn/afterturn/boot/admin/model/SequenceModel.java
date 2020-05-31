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

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 序列管理
 *
 * @author JueYue
 * @Date 2018-09-18 11:12:33
 */
@Data
@ApiModel("序列管理")
@TableName(value = "t_base_sequence", resultMap = "BaseResultMap")
public class SequenceModel extends Model<SequenceModel> {

    private static final long serialVersionUID = 1L;

    public SequenceModel() {
    }

    public SequenceModel(String name, int currentValue, int increment) {
        this.name = name;
        this.currentValue = currentValue;
        this.increment = increment;
    }


    @Excel(name = "序列名称")
    @TableField(value = "name", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("序列名称")
    private String name;

    @Excel(name = "描述")
    @TableField(value = "DESC", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("描述")
    private String desc;

    @Excel(name = "当前值")
    @TableField(value = "current_value")
    @ApiModelProperty("当前值")
    private Integer currentValue;

    @Excel(name = "步长")
    @TableField(value = "increment")
    @ApiModelProperty("步长")
    private Integer increment;

    @Excel(name = "修改时间")
    @TableField(value = "update_time")
    @ApiModelProperty("修改时间")
    private Date updateTime;

    @Override
    protected Serializable pkVal() {
        return name;
    }
}

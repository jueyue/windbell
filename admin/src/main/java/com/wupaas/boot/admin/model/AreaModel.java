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
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 地区表
 *
 * @author JueYue
 * @Date 2018-11-12 15:53:51
 */
@Data
@ApiModel("地区表")
@TableName(value = "t_base_area", resultMap = AreaModel.BASE_RESULT_MAP)
public class AreaModel extends IdBaseModel<AreaModel> {

    private static final long serialVersionUID = 1L;

    @TableField(value = "NAME", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("地区")
    private String name;

    @TableField(value = "PARENTID")
    @ApiModelProperty("上级")
    private Integer parentid;

    @TableField(value = "SHORTNAME", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("简称")
    private String shortname;

    @TableField(value = "LEVELTYPE")
    @ApiModelProperty("级别")
    private Integer leveltype;

    @TableField(value = "CITYCODE", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("城市编码")
    private String citycode;

    @TableField(value = "ZIPCODE", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("地区码")
    private String zipcode;

    @TableField(value = "MERGERNAME", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("全称")
    private String mergername;

    @TableField(value = "LNG")
    @ApiModelProperty("经度")
    private Float lng;

    @TableField(value = "LAT")
    @ApiModelProperty("纬度")
    private Float lat;

    @TableField(value = "PINYIN", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("拼音")
    private String pinyin;
}

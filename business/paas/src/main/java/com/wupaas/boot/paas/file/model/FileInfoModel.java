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
package com.wupaas.boot.paas.file.model;

import com.wupaas.boot.core.business.model.IdTenantBaseModel;
import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 文件信息
 *
 * @author JueYue
 * @Date 2019-11-14 11:14:44
 */
@Data
@ApiModel("文件信息")
@TableName(value = "p_file_info", resultMap = FileInfoModel.BASE_RESULT_MAP)
public class FileInfoModel extends IdTenantBaseModel<FileInfoModel> {

    private static final long serialVersionUID = 1L;


    @Excel(name = "文件地址")
    @TableField(value = "PATH", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("文件地址")
    private String path;

    @Excel(name = "状态")
    @TableField(value = "STATUS")
    @ApiModelProperty("状态")
    private Integer status;

    @Excel(name = "文件类型")
    @TableField(value = "TYPE")
    @ApiModelProperty("文件类型")
    private Integer type;

    @Excel(name = "三方渠道")
    @TableField(value = "CHANNEL_ID", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("三方渠道")
    private String channelId;

}

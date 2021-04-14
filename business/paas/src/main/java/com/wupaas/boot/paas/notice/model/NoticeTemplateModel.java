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
package com.wupaas.boot.paas.notice.model;

import com.wupaas.boot.core.business.model.IdBaseModel;
import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 通知模板
 *
 * @author JueYue
 * @Date 2019-08-16 17:09:52
 */
@Data
@ApiModel("通知模板")
@TableName(value = "p_notice_template", resultMap = NoticeTemplateModel.BASE_RESULT_MAP)
public class NoticeTemplateModel extends IdBaseModel<NoticeTemplateModel> {

    private static final long serialVersionUID = 1L;

    public NoticeTemplateModel(){}
    public NoticeTemplateModel(String code){
        this.code = code;
    }


    @Excel(name = "模板名称")
    @TableField(value = "name", strategy = FieldStrategy.NOT_EMPTY, condition = SqlCondition.LIKE)
    @ApiModelProperty("模板名称")
    private String name;

    @Excel(name = "模板编码")
    @TableField(value = "code", strategy = FieldStrategy.NOT_EMPTY, condition = SqlCondition.LIKE)
    @ApiModelProperty("模板编码")
    private String code;

    @Excel(name = "信息类型")
    @TableField(value = "msg_type", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("信息类型")
    private String msgType;

    @Excel(name = "是否营销短信")
    @TableField(value = "marketing_msg")
    @ApiModelProperty("是否营销短信")
    private Integer marketingMsg;

    @Excel(name = "模板内容")
    @TableField(value = "content", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("模板内容")
    private String content;

    @Excel(name = "链接")
    @TableField(value = "link", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("链接")
    private String link;

    @Excel(name = "指定渠道")
    @TableField(value = "channel_code", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("指定渠道")
    private String channelCode;

    @Excel(name = "业务子通道")
    @TableField(value = "ext_sub_code", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("业务子通道")
    private String extSubCode;

    @Excel(name = "状态")
    @TableField(value = "status", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("状态")
    private String status;

    @Excel(name = "三方模板")
    @TableField(value = "third_template_code", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("三方模板")
    private String thirdTemplateCode;

}

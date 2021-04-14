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
package com.wupaas.boot.paas.notice.model;

import com.wupaas.boot.core.business.model.IdTenantBaseModel;
import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 通知表
 *
 * @author JueYue
 * @Date 2019-08-29 10:40:45
 */
@Data
@ApiModel("通知表")
@TableName(value = "p_notice", resultMap = NoticeModel.BASE_RESULT_MAP)
public class NoticeModel extends IdTenantBaseModel<NoticeModel> {

    private static final long serialVersionUID = 1L;

    @Excel(name = "标题")
    @TableField(value = "TITLE", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("标题")
    private String title;

    @Excel(name = "类型")
    @TableField(value = "TYPE")
    @ApiModelProperty("类型")
    private Integer type;

    @Excel(name = "内容")
    @TableField(value = "CONTENT", strategy = FieldStrategy.NOT_EMPTY, condition = SqlCondition.LIKE)
    @ApiModelProperty("内容")
    private String content;

    @Excel(name = "模板")
    @TableField(value = "TEMPLATE_ID", strategy = FieldStrategy.NOT_EMPTY, condition = SqlCondition.LIKE)
    @ApiModelProperty("模板")
    private String templateId;

    @Excel(name = "开始时间")
    @TableField(value = "START_TIME")
    @ApiModelProperty("开始时间")
    private Date startTime;

    @Excel(name = "结束时间")
    @TableField(value = "END_TIME")
    @ApiModelProperty("结束时间")
    private Date endTime;

    @Excel(name = "发送时间")
    @TableField(value = "SEND_TIME")
    @ApiModelProperty("发送时间")
    private Date sendTime;

    @Excel(name = "角色列表")
    @TableField(value = "ROLES_IDS", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("角色列表")
    private String rolesIds;

    @Excel(name = "用户列表")
    @TableField(value = "USER_IDS", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("用户列表")
    private String userIds;

    @Excel(name = "部门列表")
    @TableField(value = "DEPT_IDS", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("部门列表")
    private String deptIds;

    @Excel(name = "地址列表（泛化，邮件，电话）")
    @TableField(value = "ADDRESS", strategy = FieldStrategy.NOT_EMPTY, condition = SqlCondition.LIKE)
    @ApiModelProperty("地址列表（泛化，邮件，电话）")
    private String address;

    @Excel(name = "渠道")
    @TableField(value = "CHANNEL", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("渠道")
    private String channel;

    @Excel(name = "状态")
    @TableField(value = "status", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("状态")
    private String status;

}

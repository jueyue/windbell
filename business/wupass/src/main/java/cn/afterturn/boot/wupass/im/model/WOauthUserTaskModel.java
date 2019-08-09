/**
 * Copyright 2017-2018 JueYue (qrb.jueyue@foxmail.com)
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.afterturn.boot.wupass.im.model;

import cn.afterturn.boot.bussiness.model.IdTenantBaseModel;
import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import cn.afterturn.easypoi.excel.annotation.Excel;

/**
 * 刷手任务信息
 *
 * @author JueYue
 * @Date 2019-08-07 20:31:54
 */
@Data
@ApiModel("刷手任务信息")
@TableName(value = "w_oauth_user_task", resultMap = WOauthUserTaskModel .BASE_RESULT_MAP)
public class WOauthUserTaskModel extends IdTenantBaseModel<WOauthUserTaskModel> {

    private static final long serialVersionUID = 1L;


    @Excel(name = "ID")
    @TableId(value = "id",type = IdType.AUTO)
    @ApiModelProperty("ID")
    private String id;

    @Excel(name = "租户ID")
    @TableField(value="TENANT_ID" , strategy = FieldStrategy.NOT_EMPTY )
    @ApiModelProperty("租户ID")
    private String tenantId;


    @Excel(name = "创建时间")
    @TableField(value="CRT_TIME"  )
    @ApiModelProperty("创建时间")
    private Date crtTime;


    @Excel(name = "修改时间")
    @TableField(value="MDF_TIME"  )
    @ApiModelProperty("修改时间")
    private Date mdfTime;

    @Excel(name = "任务ID")
    @TableField(value="TASK_ID" , strategy = FieldStrategy.NOT_EMPTY )
    @ApiModelProperty("任务ID")
    private String taskId;

    @Excel(name = "刷手")
    @TableField(value="OAUTH_USER_ID" , strategy = FieldStrategy.NOT_EMPTY )
    @ApiModelProperty("刷手")
    private String oauthUserId;

    @Excel(name = "任务状态")
    @TableField(value="TASK_STATUS" , strategy = FieldStrategy.NOT_EMPTY )
    @ApiModelProperty("任务状态")
    private String taskStatus;

    @Excel(name = "描述")
    @TableField(value="DESC" , strategy = FieldStrategy.NOT_EMPTY )
    @ApiModelProperty("描述")
    private String desc;

    @Excel(name = "评价")
    @TableField(value="EVALUATE" , strategy = FieldStrategy.NOT_EMPTY )
    @ApiModelProperty("评价")
    private String evaluate;
}

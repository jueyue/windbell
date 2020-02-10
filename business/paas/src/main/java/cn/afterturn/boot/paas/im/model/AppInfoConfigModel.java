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
package cn.afterturn.boot.paas.im.model;

import cn.afterturn.boot.bussiness.model.IdTenantBaseModel;
import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 三方平台APP信息
 *
 * @author JueYue
 * @Date 2019-06-27 16:40:25
 */
@Data
@ApiModel("三方平台APP信息")
@TableName(value = "p_app_info_config", resultMap = AppInfoConfigModel.BASE_RESULT_MAP)
public class AppInfoConfigModel extends IdTenantBaseModel<AppInfoConfigModel> {

    private static final long serialVersionUID = 1L;

    public AppInfoConfigModel() {

    }

    public AppInfoConfigModel(String type, String tenantId, String appCode) {
        this.type = type;
        this.appCode = appCode;
        super.setTenantId(tenantId);
    }

    @Excel(name = "类型")
    @TableField(value = "TYPE", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("类型")
    private String type;

    @Excel(name = "AppCode")
    @TableField(value = "APP_CODE", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("AppCode")
    private String appCode;

    @Excel(name = "功能")
    @TableField(value = "APP_NAME", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("功能")
    private String appName;

    @Excel(name = "外部AppID")
    @TableField(value = "APP_ID", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("外部AppID")
    private String appId;

    @Excel(name = "APP秘钥")
    @TableField(value = "SECRET", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("APP秘钥")
    private String secret;

    @Excel(name = "子APPID")
    @TableField(value = "SUB_APP_ID", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("子APPID")
    private String subAppId;

    @Excel(name = "内部子APPID")
    @TableField(value = "SUB_APP_CODE", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("内部子APPID")
    private String subAppCode;

    @Excel(name = "子秘钥")
    @TableField(value = "SUB_APP_SECRET", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("子秘钥")
    private String subAppSecret;


}

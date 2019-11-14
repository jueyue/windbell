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
package cn.afterturn.boot.paas.risk.model;

import cn.afterturn.boot.bussiness.model.IdTenantBaseModel;
import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 身份认证
 *
 * @author JueYue
 * @Date 2019-11-14 21:09:38
 */
@Data
@ApiModel("身份认证")
@TableName(value = "p_risk_idcard", resultMap = RiskIdcardModel.BASE_RESULT_MAP)
public class RiskIdcardModel extends IdTenantBaseModel<RiskIdcardModel> {

    private static final long serialVersionUID = 1L;


    @Excel(name = "手机")
    @TableField(value = "MOBILE", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("手机")
    private String mobile;

    @Excel(name = "姓名")
    @TableField(value = "NAME", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("姓名")
    private String name;

    @Excel(name = "身份证")
    @TableField(value = "ID_CARD", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("身份证")
    private String idCard;

    @Excel(name = "银行卡")
    @TableField(value = "CARD_NO", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("银行卡")
    private String cardNo;

    @Excel(name = "类型")
    @TableField(value = "TYPE")
    @ApiModelProperty("类型")
    private Integer type;

    @Excel(name = "三方渠道")
    @TableField(value = "CHANNEL_ID", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("三方渠道")
    private String channelId;

    @Excel(name = "认证结果")
    @TableField(value = "RESULT_INFO", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("认证结果")
    private String resultInfo;
}

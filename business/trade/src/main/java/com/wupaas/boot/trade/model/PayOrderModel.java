/**
 * Copyright 2017-2019 JueYue (qrb.jueyue@foxmail.com)
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
package com.wupaas.boot.trade.model;

import com.wupaas.boot.bussiness.model.IdTenantBaseModel;
import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import cn.afterturn.easypoi.excel.annotation.Excel;

/**
 * 支付订单
 *
 * @author JueYue
 * @Date 2019-08-11 12:49:07
 */
@Data
@ApiModel("支付订单")
@TableName(value = "pay_order", resultMap = PayOrderModel .BASE_RESULT_MAP)
public class PayOrderModel extends IdTenantBaseModel<PayOrderModel> {

    private static final long serialVersionUID = 1L;


    @Excel(name = "订单号")
    @TableField(value="orderid" , strategy = FieldStrategy.NOT_EMPTY )
    @ApiModelProperty("订单号")
    private String orderid;

    @Excel(name = "购买用户ID")
    @TableField(value="user_id"  )
    @ApiModelProperty("购买用户ID")
    private Integer userId;

    @Excel(name = "用户类型")
    @TableField(value="user_type" , strategy = FieldStrategy.NOT_EMPTY )
    @ApiModelProperty("用户类型")
    private String userType;

    @Excel(name = "商品描述")
    @TableField(value="goods_desc" , strategy = FieldStrategy.NOT_EMPTY )
    @ApiModelProperty("商品描述")
    private String goodsDesc;

    @Excel(name = "产品类型")
    @TableField(value="type" , strategy = FieldStrategy.NOT_EMPTY )
    @ApiModelProperty("产品类型")
    private String type;

    @Excel(name = "订单名称")
    @TableField(value="subject" , strategy = FieldStrategy.NOT_EMPTY )
    @ApiModelProperty("订单名称")
    private String subject;

    @Excel(name = "支付渠道")
    @TableField(value="channel"  )
    @ApiModelProperty("支付渠道")
    private Integer channel;

    @Excel(name = "上游订单号")
    @TableField(value="up_order_id" , strategy = FieldStrategy.NOT_EMPTY )
    @ApiModelProperty("上游订单号")
    private String upOrderId;

    @Excel(name = "上游渠道")
    @TableField(value="up_channel"  )
    @ApiModelProperty("上游渠道")
    private Integer upChannel;

    @Excel(name = "购买者")
    @TableField(value="buyer_id" , strategy = FieldStrategy.NOT_EMPTY )
    @ApiModelProperty("购买者")
    private String buyerId;

    @Excel(name = "产品价格")
    @TableField(value="price" , strategy = FieldStrategy.NOT_EMPTY )
    @ApiModelProperty("产品价格")
    private String price;

    @Excel(name = "状态")
    @TableField(value="status"  )
    @ApiModelProperty("状态")
    private Integer status;

    @Excel(name = "Memo")
    @TableField(value="memo" , strategy = FieldStrategy.NOT_EMPTY )
    @ApiModelProperty("Memo")
    private String memo;

    @Excel(name = "支付相关信息")
    @TableField(value="info" , strategy = FieldStrategy.NOT_EMPTY )
    @ApiModelProperty("支付相关信息")
    private String info;

    @Excel(name = "支付时间")
    @TableField(value="pay_time"  )
    @ApiModelProperty("支付时间")
    private Date payTime;

}

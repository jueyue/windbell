package cn.afterturn.boot.trade.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NonNull;

/**
 * @author by jueyue on 18-6-1.
 */
@ApiModel("代付参数")
@Data
public class PaymentModel {

    @ApiModelProperty("持卡人")
    private String toCardHolder;
    @ApiModelProperty("身份证")
    private String toCardIdentity;
    @ApiModelProperty("手机号")
    private String toCardMobile;
    @ApiModelProperty("卡号")
    private String toCardNo;
    @ApiModelProperty("联行号")
    private String toCardSubBankCode;
    @ApiModelProperty("联行名称")
    private String toCardSubBankName;
    @ApiModelProperty("卡类型")
    private String toCardType;


}

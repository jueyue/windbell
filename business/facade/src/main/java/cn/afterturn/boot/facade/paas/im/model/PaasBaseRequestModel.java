package cn.afterturn.boot.facade.paas.im.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author by jueyue on 19-6-30.
 */
@Data
public class PaasBaseRequestModel {

    @ApiModelProperty("客户号")
    private String tenantId;

    @ApiModelProperty("App编码")
    private String appCode;
}

package cn.afterturn.boot.paas.im.controller.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author jueyue on 20-2-11.
 */
@Data
public class PaasDeptRequestModel extends PaasBaseRequestModel {

    @ApiModelProperty("父部门ID")
    private String pid;

    @ApiModelProperty("简称")
    private String simpleName;

    @ApiModelProperty("全称")
    private String fullName;

    @ApiModelProperty("部门编码")
    private String deptCode;
}

package cn.afterturn.boot.facade.admin.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @author
 * @Date 2018-09-03 23:30:16
 */
@Data
@ApiModel("")
public class TenantEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("租户")
    private String tenantId;

    @ApiModelProperty("租户名称")
    private String tenantName;

    @ApiModelProperty("管理员")
    private Long userId;

    @ApiModelProperty("类型")
    private String merType;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("状态")
    private String status;

    @ApiModelProperty("联系人")
    private String linkman;

    @ApiModelProperty("手机")
    private String phone;

    @Excel(name = "短信签名")
    @TableField(value = "SMS_SIGN")
    @ApiModelProperty("短信签名")
    private String smsSign;

    @Excel(name = "平台")
    @ApiModelProperty("平台")
    private String paas;

}

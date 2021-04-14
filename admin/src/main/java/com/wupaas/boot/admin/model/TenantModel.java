package com.wupaas.boot.admin.model;

import com.wupaas.boot.core.business.model.IdTenantBaseModel;
import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;


/**
 * @author
 * @Date 2018-09-03 23:30:16
 */
@Data
@ApiModel("")
@TableName(value = "t_system_tenant", resultMap = TenantModel.BASE_RESULT_MAP)
public class TenantModel extends IdTenantBaseModel<TenantModel> {

    private static final long serialVersionUID = 1L;

    public TenantModel(){}
    public TenantModel(String tenantId){this.setTenantId(tenantId);}


    @Excel(name = "管理员")
    @TableField(value = "USER_ID")
    @ApiModelProperty("管理员")
    private Long userId;

    @Excel(name = "类型")
    @TableField(value = "MER_TYPE")
    @ApiModelProperty("类型")
    private String merType;

    @Excel(name = "名称")
    @TableField(value = "NAME", condition = SqlCondition.LIKE, strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("名称")
    @NotNull
    private String name;

    @Excel(name = "状态")
    @TableField(value = "STATUS")
    @ApiModelProperty("状态")
    private String status;

    @Excel(name = "营业执照")
    @TableField(value = "CREDIT_CODE")
    @ApiModelProperty("营业执照")
    private String creditCode;

    @Excel(name = "联系人")
    @TableField(value = "LINKMAN", condition = SqlCondition.LIKE, strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("联系人")
    @NotNull
    private String linkman;

    @Excel(name = "手机")
    @TableField(value = "PHONE", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("手机")
    @NotNull
    private String phone;

    @Excel(name = "省")
    @TableField(value = "PROVINCE")
    @ApiModelProperty("省")
    private String province;

    @Excel(name = "市")
    @TableField(value = "CITY")
    @ApiModelProperty("市")
    private String city;

    @Excel(name = "地区")
    @TableField(value = "AREA")
    @ApiModelProperty("地区")
    private String area;

    @Excel(name = "地址")
    @TableField(value = "ADDRESS")
    @ApiModelProperty("地址")
    private String address;

    @Excel(name = "行业大类")
    @TableField(value = "INDUSTRY_BIG")
    @ApiModelProperty("行业大类")
    private String industryBig;

    @Excel(name = "行业小类")
    @TableField(value = "INDUSTRY_SMALL")
    @ApiModelProperty("行业小类")
    private String industrySmall;

    @Excel(name = "短信签名")
    @TableField(value = "SMS_SIGN")
    @ApiModelProperty("短信签名")
    private String smsSign;

    @Excel(name = "平台")
    @TableField(value = "PAAS")
    @ApiModelProperty("平台")
    private String paas;

    @Excel(name = "备注信息")
    @TableField(value = "MEMO")
    @ApiModelProperty("备注信息")
    private String memo;

}

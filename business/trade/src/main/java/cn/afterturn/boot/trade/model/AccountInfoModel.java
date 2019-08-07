package cn.afterturn.boot.trade.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * 账户基本信息
 *
 * @author
 * @Date
 */

@Data
@TableName("account_info")
@ApiModel("账户")
public class AccountInfoModel extends Model<AccountInfoModel> {

    private static final long serialVersionUID = 1L;

    public AccountInfoModel() {
    }

    public AccountInfoModel(String accountNo) {
        this.accountNo = accountNo;
    }

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 账户
     */
    @TableField(value = "account_no")
    @ApiModelProperty("账号")
    private String accountNo;
    /**
     * 账户名称
     */
    @TableField(value = "name")
    @ApiModelProperty("账号ID")
    private String name;
    /**
     * 应用
     */
    @TableField(value = "appid")
    @ApiModelProperty("AppId")
    private String appid;
    /**
     * 类型
     */
    @TableField(value = "type")
    @ApiModelProperty("账号类型")
    private Integer type;
    /**
     * 余额
     */
    @TableField(value = "balance")
    @ApiModelProperty("账号余额")
    private Long balance;
    /**
     * 余额
     */
    @TableField(value = "frozen_balance")
    @ApiModelProperty("不可提现账号余额")
    private Long frozenBalance;
    /**
     * 币种
     */
    @TableField(value = "currency")
    private Integer currency;
    /**
     * 开户时间
     */
    @TableField(value = "open_time")
    private Date openTime;
    /**
     * 是否允许充值
     */
    @TableField(value = "is_recharge")
    private Integer isRecharge;
    /**
     * 是否允许提现
     */
    @TableField(value = "is_cash")
    private Integer isCash;
    /**
     * 是否允许余额为负
     */
    @TableField(value = "is_balance_negative")
    private Integer isBalanceNegative;
    /**
     * 用户名称
     */
    @TableField(value = "user_name")
    private String userName;
    /**
     * 用户手机
     */
    @TableField(value = "user_phone")
    private String userPhone;

    /**
     * 创建时间
     */
    @TableField(value = "crt_time")
    private Date crtTime;
    /**
     * 创建用户
     */
    @TableField(value = "crt_user_id")
    private String crtUserId;
    /**
     * 修改时间
     */
    @TableField(value = "mdf_time")
    private Date mdfTime;
    /**
     * 修改用户
     */
    @TableField(value = "mdf_user_id")
    private String mdfUserId;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}

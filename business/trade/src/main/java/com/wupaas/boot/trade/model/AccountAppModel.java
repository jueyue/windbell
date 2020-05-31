package com.wupaas.boot.trade.model;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * 应用
 *
 * @author
 * @Date
 */
@Data
@TableName("account_app")
public class AccountAppModel extends Model<AccountAppModel> {

    private static final long serialVersionUID = 1L;


    public AccountAppModel() {

    }

    public AccountAppModel(String appId) {
        this.appId = appId;
    }

    /**
     * Id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * AppId
     */
    @TableField(value = "app_id")
    private String  appId;
    /**
     * 租户Id
     */
    @TableField(value = "tenant_id")
    private String  tenantId;
    /**
     * 账户
     */
    @TableField(value = "account_no")
    private String  accountNo;
    /**
     * App名称
     */
    @TableField(value = "app_name")
    private String  appName;
    /**
     * 真正付款
     */
    @TableField(value = "real_pay")
    private String  realPay;

    @TableField(exist = false)
    private String balance;
    /**
     * 创建时间
     */
    @TableField(value = "crt_time")
    private Date   crtTime;
    /**
     * 创建用户
     */
    @TableField(value = "crt_user_id")
    private String crtUserId;
    /**
     * 修改时间
     */
    @TableField(value = "mdf_time")
    private Date   mdfTime;
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

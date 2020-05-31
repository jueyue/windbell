
package com.wupaas.boot.trade.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.io.Serializable;

/**
 * 总分平衡
 *
 * @author JueYue
 * @Date 2018-09-12 19:47:54
 */
@Data
@TableName("account_balance_calc")
public class AccountBalanceCalcModel extends Model<AccountBalanceCalcModel> {

    private static final long serialVersionUID = 1L;


    /**
     * 转入
     */
    @TableField(value = "account_input")
    @Excel(name = "转入")
    private String accountInput;
    /**
     * 转出
     */
    @TableField(value = "account_out")
    @Excel(name = "转出")
    private String accountOut;
    /**
     * 余额
     */
    @TableField(value = "balance")
    @Excel(name = "余额")
    private String balance;
    /**
     * 账户
     */
    @TableField(value = "account")
    @Excel(name = "账户")
    private String account;
    /**
     * 用户
     */
    @TableField(value = "name")
    @Excel(name = "用户")
    private String name;
    /**
     * 计算余额
     */
    @TableField(value = "calc_balance")
    @Excel(name = "计算余额")
    private String calcBalance;
    /**
     * 误差
     */
    @TableField(value = "error_range")
    @Excel(name = "误差")
    private String errorRange;

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}

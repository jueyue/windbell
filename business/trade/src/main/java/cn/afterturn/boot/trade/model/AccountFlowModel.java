package cn.afterturn.boot.trade.model;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;


import lombok.Data;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * 账户流水号
 *
 * @author
 * @Date
 */
@Data
@TableName("account_flow")
public class AccountFlowModel extends Model<AccountFlowModel> {

    private static final long serialVersionUID = 1L;

    public AccountFlowModel(){}

    public AccountFlowModel(String tradeNo){
        this.tradeNo = tradeNo;
    }

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 订单号
     */
    @TableField(value = "trade_no")
    @Excel(name = "订单号",width = 30)
    private String tradeNo;
    /**
     * 账户号
     */
    @TableField(value = "account_no")
    @Excel(name = "账户号",width = 20)
    private String accountNo;
    /**
     * 交易时间
     */
    @TableField(value = "trade_time")
    @Excel(name = "交易时间", format = "yyyy-MM-dd HH:mm:ss",width = 20)
    private Date tradeTime;
    /**
     * 交易时间
     */
    @TableField(value = "third_trade_time")
    @Excel(name = "交易时间", format = "yyyy-MM-dd HH:mm:ss",width = 20)
    private Date thirdTradeTime;
    /**
     * 科目
     */
    @TableField(value = "subject")
    @Excel(name = "科目",width = 20)
    private String subject;
    /**
     * 类型
     */
    @TableField(value = "type")
    @Excel(name = "自定义类型",width = 20)
    private String type;
    /**
     * 关联订单号
     */
    @TableField(value = "outer_trade_no")
    @Excel(name = "外部订单号")
    private String outerTradeNo;
    /**
     * 币种
     */
    @TableField(value = "currency")
    private Integer currency;
    /**
     * 金额
     */
    @Excel(name = "交易金额",numFormat = "#.00")
    @TableField(value = "money")
    private Integer money;
    /**
     * 手续费
     */
    @TableField(value = "fee")
    private Integer fee;
    /**
     * 到账金额
     */
    @Excel(name = "到账金额")
    @TableField(value = "real_money")
    private Integer realMoney;
    /**
     * 余额
     */
    @Excel(name = "余额")
    @TableField(value = "balance")
    private Long balance;
    /**
     * 交易前余额
     */
    @Excel(name = "交易前余额")
    @TableField(value = "before_balance")
    private Long beforeBalance;
    /**
     * 交易信息
     */
    @TableField(value = "trade_info")
    private String tradeInfo;
    /**
     * 完成时间
     */
    @TableField(value = "complete_time")
    private Date completeTime;
    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;
    /**
     * 状态 : 1成功,2付款中,3失败,6 待解冻, 7已解冻
     */
    @TableField(value = "status")
    private Integer status;
    /**
     * 备注
     */
    @TableField(value = "memo")
    @Excel(name = "备注",width = 20)
    private String memo;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}

package cn.afterturn.boot.trade.model.enums;

/**
 * @author by jueyue on 18-10-25.
 */
public interface InternalAccountEnum {

    /**
     * 总账内部户
     */
    public static final String ALL_MONEY = "AC9900100000001001";
    /**
     * 提现内部户
     */
    public static final String FORWARD = "AC9900100000006001";
    /**
     * 提现手续费内部户
     */
    public static final String FORWARD_FEE = "AC9900200000006001";
    /**
     * 冻结内部户
     */
    public static final String FROZEN = "AC9900100000009002";

}

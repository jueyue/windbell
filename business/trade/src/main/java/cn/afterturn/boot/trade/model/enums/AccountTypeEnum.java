package cn.afterturn.boot.trade.model.enums;

/**
 * @author by jueyue on 18-5-27.
 */
public enum AccountTypeEnum {

    SYSTEM(9001,"系统内部户"),
    FROZEN(9002,"冻结内部户"),
    COMPANY(1001, "公司基础账户"),
    APP(2001, "APP基础账户"),
    USER(3001, "普通基础账户");

    private int code;

    private String message;

    AccountTypeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

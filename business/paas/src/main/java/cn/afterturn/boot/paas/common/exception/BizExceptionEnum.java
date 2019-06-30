package cn.afterturn.boot.paas.common.exception;

/**
 * 所有业务异常的枚举
 *
 * @author JueYue
 * @date 2016年11月12日 下午5:04:51
 */
public enum BizExceptionEnum {

    NOT_EXISTS_APP_SECRET(-7001, "没有找到正确的秘钥"),
    GET_TOKEN_ERROR(-7002, "获取Token失败"),

    /**
     * 参数异常
     */
    REQUEST_NULL(-101, "参数无效");

    BizExceptionEnum(int code, String message) {
        this.friendlyCode = code;
        this.friendlyMsg = message;
    }

    private int friendlyCode;

    private String friendlyMsg;

    public int getCode() {
        return friendlyCode;
    }

    public void setCode(int code) {
        this.friendlyCode = code;
    }

    public String getMessage() {
        return friendlyMsg;
    }

    public void setMessage(String message) {
        this.friendlyMsg = message;
    }

}

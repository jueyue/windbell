package cn.afterturn.boot.paas.common.enums;

/**
 * 状态枚举-和字典status 保持一致,这里方便开发
 * @author by jueyue on 19-9-1.
 */
public enum  StatusEnum {

    NORMAL("1", "启用");

    StatusEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private String code;

    private String message;


    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

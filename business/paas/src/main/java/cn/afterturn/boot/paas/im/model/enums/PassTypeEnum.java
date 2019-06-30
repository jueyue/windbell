package cn.afterturn.boot.paas.im.model.enums;

/**
 * @author by jueyue on 19-6-30.
 */
public enum PassTypeEnum {

    WEI_XIN("1", "企业微信"),
    DING_DING("2", "钉钉");

    PassTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    private String code;

    private String name;

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}

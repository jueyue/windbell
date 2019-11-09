package cn.afterturn.boot.facade.paas.msg.entity.enums;

/**
 * @author by jueyue on 19-9-11.
 * 渠道(APP,小程序,公众号,微信,钉钉,网页,短信,邮件)'
 */
public enum NoticeTypeEnum {

    MSG(1, "短信"),
    APP(2, "APP"),
    WEIXIN(3, "微信"),
    WORK_WEIXIN(4, "企业微信"),
    DING_TALK(5, "钉钉"),
    MAIL(6, "邮件"),
    PHONE(7, "电话");

    NoticeTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    private Integer code;

    private String name;

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}

package com.wupaas.boot.paas.common.enums;

/**
 * 渠道枚举
 *
 * @author jueyue on 19-11-12.
 */
public enum ChannelEnum {

    JU_HE("juhe"),
    TENCENT("tencent"),
    ALIYUN("aliyun"),
    QI_NIU("qiniu");

    private String name;

    ChannelEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static ChannelEnum to(String name) {
        ChannelEnum[] channels = values();
        for (int i = 0; i < channels.length; i++) {
            if (channels[i].getName().equalsIgnoreCase(name)) {
                return channels[i];
            }
        }
        return null;
    }


}

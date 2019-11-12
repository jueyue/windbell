package cn.afterturn.boot.paas.notice.model;

/**
 * 消息渠道枚举
 *
 * @author jueyue on 19-11-12.
 */
public enum ImChannelEnum {

    ALIYUN("aliyun");

    private String name;

    ImChannelEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static ImChannelEnum to(String name) {
        ImChannelEnum[] channels = values();
        for (int i = 0; i < channels.length; i++) {
            if (channels[i].getName().equalsIgnoreCase(name)) {
                return channels[i];
            }
        }
        return null;
    }
}

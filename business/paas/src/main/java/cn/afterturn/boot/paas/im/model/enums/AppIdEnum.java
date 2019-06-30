package cn.afterturn.boot.paas.im.model.enums;

import lombok.Data;

/**
 * @author by jueyue on 19-6-30.
 */
public enum AppIdEnum {
    /**
     * 1*** 都是paas平台自带的应用
      */
    CONTACTS(1001, "通讯录"),
    APPROVAL(1010, "审批");

    AppIdEnum(int appId, String name) {
        this.appId = appId;
        this.name = name;
    }

    private int appId;

    private String name;

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

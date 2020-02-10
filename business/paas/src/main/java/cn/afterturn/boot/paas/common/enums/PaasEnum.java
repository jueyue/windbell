package cn.afterturn.boot.paas.common.enums;

/**
 * 三方平台
 *
 * @author jueyue on 20-2-9.
 */
public enum PaasEnum {

    WEI_XIN("10"),
    DING_TALK("20");

    private String name;

    PaasEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static PaasEnum to(String name) {
        PaasEnum[] paas = values();
        for (int i = 0; i < paas.length; i++) {
            if (paas[i].getName().equalsIgnoreCase(name)) {
                return paas[i];
            }
        }
        return null;
    }
}

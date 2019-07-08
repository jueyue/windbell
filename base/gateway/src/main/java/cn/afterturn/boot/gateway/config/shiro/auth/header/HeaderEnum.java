package cn.afterturn.boot.gateway.config.shiro.auth.header;

/**
 * @author by jueyue on 19-1-30.
 */
public enum HeaderEnum {

    TOKEN("token"),
    ROLE("roleId"),
    USER_NAME("userName"),
    USER("userId"),
    PRODUCT("product");

    private String name;

    HeaderEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

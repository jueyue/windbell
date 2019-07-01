package cn.afterturn.boot.bussiness.auth.header;

/**
 * @author by jueyue on 19-1-30.
 */
public enum HeaderEnum {

    TOKEN("token"),
    ROLE("roleId"),
    USER_NAME("userName"),
    USER("userId");

    private String name;

    HeaderEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

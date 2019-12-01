package cn.afterturn.boot.bussiness.exception;

/**
 * @author JueYue
 * @Description 所有业务异常的枚举
 * @date 2018-6-29 14:32:42
 */
public enum BootExceptionEnum {

    /**
     * 文件上传
     */
    FILE_READING_ERROR(400, "FILE_READING_ERROR!"),
    FILE_NOT_FOUND(400, "FILE_NOT_FOUND!"),

    /**
     * 参数异常  600-699
     */
    REQUEST_NULL(601, "参数为空"),
    REQUEST_ERROR(600, "参数错误"),

    /**
     * 数据相关异常 700-799
     */
    DATA_SAVE_ERROR(701,"插入失败"),
    DATA_UPDATE_ERROR(701,"更新ById失败"),


    /**
     * 系统异常
     */
    SERVER_ERROR(500, "服务器异常");

    BootExceptionEnum(int code, String message) {
        this.friendlyCode = code;
        this.friendlyMsg = message;
    }

    BootExceptionEnum(int code, String message, String urlPath) {
        this.friendlyCode = code;
        this.friendlyMsg = message;
        this.urlPath = urlPath;
    }

    private int friendlyCode;

    private String friendlyMsg;

    private String urlPath;

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

    public String getUrlPath() {
        return urlPath;
    }

    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath;
    }

}

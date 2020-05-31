package com.wupaas.boot.bussiness.exception;

import com.wupaas.boot.core.support.StrKit;

/**
 * @author JueYue
 * @Description 业务异常的封装
 * @date 2016年11月12日 下午5:05:10
 */
public class BootException extends RuntimeException {

    /**
     * 友好提示的code码
     */
    protected int friendlyCode;

    /**
     * 友好提示
     */
    protected String friendlyMsg;

    /**
     * 业务异常跳转的页面
     */
    protected String urlPath;

    public BootException(int friendlyCode, String friendlyMsg, String urlPath) {
        this.setValues(friendlyCode, friendlyMsg, urlPath);
    }

    public BootException(int friendlyCode, String friendlyMsg) {
        this.setValues(friendlyCode, friendlyMsg, null);
    }

    public BootException(Throwable e) {
        super(e.getMessage(), e);
    }

    public BootException(String messageTemplate, Object... params) {
        super(StrKit.format(messageTemplate, params));
    }

    public BootException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public BootException(Throwable throwable, String messageTemplate, Object... params) {
        super(StrKit.format(messageTemplate, params), throwable);
    }

    public BootException(RuntimeException run) {
        super(run);
    }

    public BootException(String friendlyMsg) {
        this.setValues(500, friendlyMsg, null);
    }

    public BootException(Exception run) {
        super(run);
    }

    public BootException(BootExceptionEnum bizExceptionEnum) {
        this.setValues(bizExceptionEnum.getCode(), bizExceptionEnum.getMessage(), bizExceptionEnum.getUrlPath());
    }

    private void setValues(int friendlyCode, String friendlyMsg, String urlPath) {
        this.friendlyCode = friendlyCode;
        this.friendlyMsg = friendlyMsg;
        this.urlPath = urlPath;
    }

    public int getCode() {
        return friendlyCode;
    }

    public void setCode(int code) {
        this.friendlyCode = code;
    }

    @Override
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

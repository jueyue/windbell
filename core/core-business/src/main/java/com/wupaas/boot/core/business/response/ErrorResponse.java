package com.wupaas.boot.core.business.response;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回给前台的错误提示
 *
 * @author fengshuonan
 * @date 2016年11月12日 下午5:05:22
 */
public class ErrorResponse extends Response {

    private static final Map EMPTY_MAP =  new HashMap();

    public ErrorResponse(int code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
        this.data = EMPTY_MAP;
    }
    public ErrorResponse() {
        super();
        this.code = 500;
        this.msg = "失败";
        this.data = EMPTY_MAP;
    }
}

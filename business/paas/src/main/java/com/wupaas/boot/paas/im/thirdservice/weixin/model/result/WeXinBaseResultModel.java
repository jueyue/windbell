package com.wupaas.boot.paas.im.thirdservice.weixin.model.result;

import lombok.Data;

/**
 * @author by dbinary on 19-6-30.
 */
@Data
public class WeXinBaseResultModel {
    /**
     * 出错返回码，为0表示成功，非0表示调用失败
     */
    private int errcode;

    /**
     * 返回码提示语
     */
    private String errmsg;

    public boolean notSuccess() {
        return errcode != 0;
    }
    public boolean isSuccess() {
        return errcode == 0;
    }
}

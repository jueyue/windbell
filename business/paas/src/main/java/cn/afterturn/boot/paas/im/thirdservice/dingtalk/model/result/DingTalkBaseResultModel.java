package cn.afterturn.boot.paas.im.thirdservice.dingtalk.model.result;

import lombok.Data;

/**
 * @author by jueyue on 19-7-14.
 */
@Data
public class DingTalkBaseResultModel {

    /**
     * 错误代码
     */
    private int errcode;

    /**
     * 错误信息
     */
    private String errmsg;

    public boolean notSuccess() {
        return errcode != 0;
    }

    public boolean isSuccess() {
        return errcode == 0;
    }
}

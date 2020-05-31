package com.wupaas.boot.paas.notice.thirdservice;

import com.wupaas.boot.paas.common.client.IClient;

import java.util.Map;

/**
 * 短信发送接口
 *
 * @author jueyue on 19-11-11.
 */
public interface ISmsSendClient extends IClient {

    /**
     * 发送短信
     *
     * @param phone      手机
     * @param templateId 模板
     * @param sign       签名
     * @param data       模板数据
     * @param content    内容
     * @return 是否成功
     */
    boolean send(String phone, String templateId, String sign, Map<String, Object> data, String content);
}

package com.wupaas.boot.paas.risk.thirdservice;

import com.wupaas.boot.paas.common.client.IClient;

/**
 * 短信发送接口
 *
 * @author jueyue on 19-11-11.
 */
public interface IIdCardAuthClient extends IClient {

    /**
     * 三要数认证
     *
     * @param cardNo
     * @param name
     * @param idcard
     * @return
     */
    String idcardAuth(String cardNo, String name, String idcard);
}

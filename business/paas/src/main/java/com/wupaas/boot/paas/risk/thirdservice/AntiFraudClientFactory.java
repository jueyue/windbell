package com.wupaas.boot.paas.risk.thirdservice;

import com.wupaas.boot.paas.common.enums.ChannelEnum;
import com.wupaas.boot.paas.risk.thirdservice.tencent.clients.AntiFraudClientOfTencent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 用户认证服务
 *
 * @author jueyue on 19-11-12.
 */
@Component
public class AntiFraudClientFactory {

    @Value("${risk.fraud.channelId}")
    private String channelId;

    @Resource(name = "antiFraudClientOfTencent")
    private AntiFraudClientOfTencent antiFraudClientOfTencent;

    public IAntiFraudClient get() {
        ChannelEnum channelEnum = ChannelEnum.to(channelId);
        switch (channelEnum) {
            case TENCENT:
                return antiFraudClientOfTencent;
        }
        return antiFraudClientOfTencent;
    }
}

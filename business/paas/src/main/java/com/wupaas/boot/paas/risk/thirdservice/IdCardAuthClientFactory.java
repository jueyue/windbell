package com.wupaas.boot.paas.risk.thirdservice;

import com.wupaas.boot.paas.common.enums.ChannelEnum;
import com.wupaas.boot.paas.risk.thirdservice.juhe.clients.IdCardAuthClientOfJuHe;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 用户认证服务
 *
 * @author jueyue on 19-11-12.
 */
@Component
public class IdCardAuthClientFactory {

    @Value("${risk.idcard.channelId}")
    private String channelId;

    @Resource(name = "idCardAuthClientOfJuHe")
    private IdCardAuthClientOfJuHe idCardAuthClientOfJuHe;

    public IIdCardAuthClient get() {
        ChannelEnum channelEnum = ChannelEnum.to(channelId);
        switch (channelEnum) {
            case JU_HE:
                return idCardAuthClientOfJuHe;
        }
        return idCardAuthClientOfJuHe;
    }
}

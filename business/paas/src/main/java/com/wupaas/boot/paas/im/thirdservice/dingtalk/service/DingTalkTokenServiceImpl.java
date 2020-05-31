package com.wupaas.boot.paas.im.thirdservice.dingtalk.service;

import com.wupaas.boot.paas.common.exception.PaasBizException;
import com.wupaas.boot.paas.common.exception.PaasBizExceptionEnum;
import com.wupaas.boot.paas.im.model.AppInfoConfigModel;
import com.wupaas.boot.paas.im.service.IAppInfoConfigService;
import com.wupaas.boot.paas.im.service.ITokenService;
import com.wupaas.boot.paas.im.thirdservice.dingtalk.clients.IDingTalkTokenClient;
import com.wupaas.boot.paas.im.thirdservice.dingtalk.model.result.DingTalkTokenResultModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author by jueyue on 19-7-15.
 */
@Slf4j
@Service("dingTalkTokenService")
public class DingTalkTokenServiceImpl implements ITokenService {

    @Autowired
    private IDingTalkTokenClient dingTalkTokenClient;

    @Autowired
    private IAppInfoConfigService appInfoConfigService;

    @Override
    public String getToken(String tenantId, String appType, String appCode) {
        AppInfoConfigModel       configModel = appInfoConfigService.getSecret(tenantId, appType, appCode);
        DingTalkTokenResultModel resultModel = dingTalkTokenClient.getToken(configModel.getAppId(), configModel.getSecret());
        if (resultModel.isSuccess()) {
            return resultModel.getAccessToken();
        }
        throw new PaasBizException(PaasBizExceptionEnum.GET_TOKEN_ERROR);
    }
}

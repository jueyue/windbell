package com.wupaas.boot.paas.im.thirdservice.weixin.service;

import com.wupaas.boot.paas.common.exception.PaasBizException;
import com.wupaas.boot.paas.common.exception.PaasBizExceptionEnum;
import com.wupaas.boot.paas.im.model.AppInfoConfigModel;
import com.wupaas.boot.paas.im.service.IAppInfoConfigService;
import com.wupaas.boot.paas.im.service.ITokenService;
import com.wupaas.boot.paas.im.thirdservice.weixin.clients.IWeiXinTokenClient;
import com.wupaas.boot.paas.im.thirdservice.weixin.model.result.WeiXinTokenResultModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author by jueyue on 19-7-15.
 */
@Slf4j
@Service("weiXinTokenService")
public class WeiXinTokenServiceImpl implements ITokenService {

    @Autowired
    private IWeiXinTokenClient weiXinTokenClient;

    @Autowired
    private IAppInfoConfigService appInfoConfigService;

    @Override
    public String getToken(String tenantId, String appType, String appCode) {
        AppInfoConfigModel     configModel = appInfoConfigService.getSecret(tenantId, appType, appCode);
        WeiXinTokenResultModel resultModel = weiXinTokenClient.getToken(configModel.getAppId(), configModel.getSecret());
        if (resultModel.isSuccess()) {
            return resultModel.getAccessToken();
        }
        throw new PaasBizException(PaasBizExceptionEnum.GET_TOKEN_ERROR);
    }
}

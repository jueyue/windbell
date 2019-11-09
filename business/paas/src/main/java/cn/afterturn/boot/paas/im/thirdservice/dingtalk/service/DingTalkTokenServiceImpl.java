package cn.afterturn.boot.paas.im.thirdservice.dingtalk.service;

import cn.afterturn.boot.paas.common.exception.BizException;
import cn.afterturn.boot.paas.common.exception.BizExceptionEnum;
import cn.afterturn.boot.paas.im.model.AppInfoConfigModel;
import cn.afterturn.boot.paas.im.service.IAppInfoConfigService;
import cn.afterturn.boot.paas.im.service.ITokenService;
import cn.afterturn.boot.paas.im.thirdservice.dingtalk.clients.IDingTalkTokenClient;
import cn.afterturn.boot.paas.im.thirdservice.dingtalk.model.result.DingTalkTokenResultModel;
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
        throw new BizException(BizExceptionEnum.GET_TOKEN_ERROR);
    }
}

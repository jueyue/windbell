package cn.afterturn.boot.paas.im.thirdservice.weixin;

import cn.afterturn.boot.core.cache.CacheKey;
import cn.afterturn.boot.core.cache.CacheKit;
import cn.afterturn.boot.paas.common.exception.BizException;
import cn.afterturn.boot.paas.common.exception.BizExceptionEnum;
import cn.afterturn.boot.paas.im.controller.model.enums.AppIdEnum;
import cn.afterturn.boot.paas.im.controller.model.enums.PassTypeEnum;
import cn.afterturn.boot.paas.im.model.AppInfoConfigModel;
import cn.afterturn.boot.paas.im.service.IAppInfoConfigService;
import cn.afterturn.boot.paas.im.thirdservice.weixin.clients.IWeiXinTokenClient;
import cn.afterturn.boot.paas.im.thirdservice.weixin.model.result.WeiXinTokenResultModel;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author by jueyue on 19-6-30.
 */
@Slf4j
@Component
public class WeiXinServiceImpl {

    @Value("${spring.application.name}")
    private String                applicationName;
    @Autowired
    private IAppInfoConfigService appInfoConfigService;
    @Autowired
    private IWeiXinTokenClient    weiXinTokenClient;

    public String getToken(String tenantId, int appCode) {
        String token = CacheKit.get(applicationName, CacheKey.get(tenantId).append(appCode).toString());
        if (StringUtils.isEmpty(token)) {
            AppInfoConfigModel companyCode = appInfoConfigService.getOne(new AppInfoConfigModel(PassTypeEnum.WEI_XIN.getCode(), tenantId, AppIdEnum.ENTERPRISE.getAppId() + ""));
            if (companyCode == null || StringUtils.isEmpty(companyCode.getSecret())) {
                throw new BizException(BizExceptionEnum.NOT_EXISTS_APP_SECRET);
            }
            AppInfoConfigModel app = appInfoConfigService.getOne(new AppInfoConfigModel(PassTypeEnum.WEI_XIN.getCode(), tenantId, appCode + ""));
            if (app == null || StringUtils.isEmpty(app.getSecret())) {
                throw new BizException(BizExceptionEnum.NOT_EXISTS_APP_SECRET);
            }
            WeiXinTokenResultModel resultModel = weiXinTokenClient.getToken(companyCode.getSecret(), app.getSecret());
            if (resultModel.notSuccess()) {
                log.error("获取app应用失败,appCode " + appCode + ",错误详情" + JSON.toJSONString(resultModel));
                throw new BizException(BizExceptionEnum.GET_TOKEN_ERROR);
            }
            CacheKit.put(applicationName, CacheKey.get(tenantId).append(appCode).toString(), resultModel.getAccessToken(), resultModel.getExpiresIn() - 60);
            token = resultModel.getAccessToken();
        }
        return token;
    }


}

package cn.afterturn.boot.paas.notice.thirdservice.aliyun.clients;

import cn.afterturn.boot.paas.common.enums.ChannelEnum;
import cn.afterturn.boot.paas.notice.thirdservice.ISmsSendClient;
import com.alibaba.fastjson.JSON;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author jueyue on 19-11-11.
 */
@Slf4j
@Component("smsSendClientOfAliYun")
public class SmsSendClientOfAliYun implements ISmsSendClient {

    @Value("${im.aliyun.accessKeyId}")
    private String accessKeyId;
    @Value("${im.aliyun.accessSecret}")
    private String accessSecret;

    @Override
    public boolean send(String phone, String templateId, String sign, Map<String, Object> data, String content) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessSecret);
        IAcsClient     client  = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", sign);
        request.putQueryParameter("TemplateCode", templateId);
        request.putQueryParameter("TemplateParam", JSON.toJSONString(data));
        try {
            CommonResponse response = client.getCommonResponse(request);
            log.debug(response.getData());
            return response.getData().contains("\"Code\":\"OK\"");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return false;
    }

    @Override
    public String getChannelId() {
        return ChannelEnum.ALIYUN.getName();
    }
}

package cn.afterturn.boot.paas.risk.thirdservice.juhe.clients;

import cn.afterturn.boot.paas.common.enums.ChannelEnum;
import cn.afterturn.boot.paas.risk.thirdservice.IIdCardAuthClient;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author jueyue on 19-11-14.
 */
@Slf4j
@Component("idCardAuthClientOfJuHe")
public class IdCardAuthClientOfJuHe implements IIdCardAuthClient {

    @Value("${risk.juhe.idcard.appKey}")
    private String appKey;

    private String idcardUrl = "http://v.juhe.cn/verifybankcard3/query?key=%s&realname=%s&bankcard=%s&idcard=%s";

    @Override
    public String idcardAuth(String cardNo, String name, String idcard) {
        String result = null;
        try {
            result = HttpUtil.get(String.format(idcardUrl, appKey, name, cardNo, idcard));
            JSONObject jsonObject = JSON.parseObject(result);
            if (jsonObject.getJSONObject("result").containsKey("res") && 1 == jsonObject.getJSONObject("result").getInteger("res")) {
                return "success";
            }
            log.error("实名认证失败,返回信息:" + result);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public String getChannelId() {
        return ChannelEnum.JU_HE.getName();
    }
}

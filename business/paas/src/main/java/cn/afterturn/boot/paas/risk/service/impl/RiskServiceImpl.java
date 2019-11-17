/**
 * Copyright 2017-2018 JueYue (qrb.jueyue@foxmail.com)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.afterturn.boot.paas.risk.service.impl;

import cn.afterturn.boot.bussiness.base.service.BaseServiceCacheImpl;
import cn.afterturn.boot.paas.risk.model.RiskIdcardModel;
import cn.afterturn.boot.paas.risk.repository.RiskIdcardRepository;
import cn.afterturn.boot.paas.risk.service.IRiskService;
import cn.afterturn.boot.paas.risk.thirdservice.AntiFraudClientFactory;
import cn.afterturn.boot.paas.risk.thirdservice.IdCardAuthClientFactory;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 身份认证服务实现
 *
 * @author JueYue
 * @Date 2019-11-14 21:09:38
 */
@Service("riskIdcardService")
public class RiskServiceImpl extends BaseServiceCacheImpl<RiskIdcardRepository, RiskIdcardModel> implements IRiskService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RiskServiceImpl.class);

    @Autowired
    private RiskIdcardRepository    riskIdcardRepository;
    @Autowired
    private IdCardAuthClientFactory idCardAuthClientFactory;
    @Autowired
    private AntiFraudClientFactory  antiFraudClientFactory;

    @Override
    public boolean idcardAuth(String cardNo, String name, String idcard, String tenantId) {
        String          result = idCardAuthClientFactory.get().idcardAuth(cardNo, name, idcard);
        RiskIdcardModel model  = getModel(cardNo, name, idcard, tenantId, 2, idCardAuthClientFactory.get().getChannelId(), null);
        model.setResultInfo(result);
        save(model);
        return "success".equalsIgnoreCase(result);
    }

    @Override
    public Map<String, String> antiFraud(String phone, String name, String idcard, String tenantId) {
        Map<String, String> result = antiFraudClientFactory.get().antiFraud(phone, name, idcard);
        RiskIdcardModel     model  = getModel(null, name, idcard, tenantId, 2, idCardAuthClientFactory.get().getChannelId(), phone);
        model.setResultInfo(JSON.toJSONString(result));
        save(model);
        return result;
    }

    private RiskIdcardModel getModel(String cardNo, String name, String idcard, String tenantId, int type, String channelId, String phone) {
        RiskIdcardModel idcardModel = new RiskIdcardModel();
        idcardModel.setCardNo(cardNo);
        idcardModel.setMobile(phone);
        idcardModel.setName(name);
        idcardModel.setIdCard(idcard);
        idcardModel.setTenantId(tenantId);
        idcardModel.setType(type);
        idcardModel.setChannelId(channelId);
        return idcardModel;
    }
}

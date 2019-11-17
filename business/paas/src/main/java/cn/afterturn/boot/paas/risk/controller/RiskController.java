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
package cn.afterturn.boot.paas.risk.controller;

import cn.afterturn.boot.bussiness.response.ErrorResponse;
import cn.afterturn.boot.bussiness.response.Response;
import cn.afterturn.boot.bussiness.response.SuccessResponse;
import cn.afterturn.boot.facade.paas.risk.IAntiFraudFacade;
import cn.afterturn.boot.facade.paas.risk.IRiskIdcardFacade;
import cn.afterturn.boot.facade.paas.risk.entity.AntiFraudEntity;
import cn.afterturn.boot.paas.risk.service.IRiskService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


/**
 * 身份认证控制器
 *
 * @author JueYue
 * @Date 2019-11-14 21:09:38
 */
@Api("风险信息")
@RestController
@RequestMapping("/risk")
public class RiskController implements IRiskIdcardFacade, IAntiFraudFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(RiskController.class);

    @Autowired
    private IRiskService riskIdcardService;

    @Override
    public Response idcardAuth(String cardNo, String name, String idcard, String tenantId) {
        boolean isOK = riskIdcardService.idcardAuth(cardNo, name, idcard, tenantId);
        if (isOK) {
            return new SuccessResponse();
        }
        return new ErrorResponse();
    }

    @Override
    public Response<AntiFraudEntity> antiFraud(String phone, String name, String idcard, String tenantId) {
        Map<String, String> info = riskIdcardService.antiFraud(phone, name, idcard, tenantId);
        if (info != null) {
            AntiFraudEntity entity = new AntiFraudEntity();
            entity.setFound(info.get("found"));
            entity.setScore(info.get("score"));
            entity.setInfo(info.get("info"));
            return new SuccessResponse(entity);
        }
        return new ErrorResponse();
    }
}
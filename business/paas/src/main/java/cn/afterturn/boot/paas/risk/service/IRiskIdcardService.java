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
package cn.afterturn.boot.paas.risk.service;

import cn.afterturn.boot.bussiness.base.service.IBaseService;
import cn.afterturn.boot.paas.risk.model.RiskIdcardModel;


/**
 * 身份认证服务
 *
 * @author JueYue
 * @Date 2019-11-14 21:09:38
 */
public interface IRiskIdcardService extends IBaseService<RiskIdcardModel> {

    /**
     * 三要数认证
     * @param cardNo
     * @param name
     * @param idcard
     * @param tenantId
     * @return
     */
    boolean idcardAuth(String cardNo, String name, String idcard, String tenantId);
}

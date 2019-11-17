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
package cn.afterturn.boot.facade.paas.risk;

import cn.afterturn.boot.bussiness.response.Response;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 身份认证接口
 *
 * @author JueYue
 * @Date 2019-11-14 21:09:38
 */
@FeignClient(value = "riskIdcardFacade", contextId = "lemur-paas", path = "/risk")
public interface IRiskIdcardFacade {

    /**
     * 身份证三要数认证
     *
     * @param cardNo
     * @param name
     * @param idcard
     * @param tenantId
     * @return
     */
    @ApiOperation("三要数认证")
    @PostMapping("/idcardAuth")
    public Response idcardAuth(@ApiParam("身份证") @RequestParam(name = "cardNo") String cardNo,
                               @ApiParam("姓名") @RequestParam(name = "name") String name,
                               @ApiParam("银行卡") @RequestParam(name = "idcard") String idcard,
                               @ApiParam("客户") @RequestParam(name = "tenantId") String tenantId);
}

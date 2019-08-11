        /**
 * Copyright 2017-2019 JueYue (qrb.jueyue@foxmail.com)
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.afterturn.boot.trade.controller;

import cn.afterturn.boot.bussiness.base.controller.BaseController;
import cn.afterturn.boot.trade.model.PayOrderModel;
import cn.afterturn.boot.trade.service.IPayOrderService;

import cn.afterturn.boot.facade.IPayOrderFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 支付订单控制器
 *
 * @author JueYue
 * @Date 2019-08-11 12:49:07
 */
@Api("支付订单")
@RestController
@RequestMapping("/payorder")
public class PayOrderController extends BaseController<IPayOrderService, PayOrderModel> implements IPayOrderFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(PayOrderController.class);

    @Autowired
    private IPayOrderService payOrderService;

}
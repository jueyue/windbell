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
package cn.afterturn.boot.trade.service.impl;

import cn.afterturn.boot.trade.repository.PayOrderRepository;
import cn.afterturn.boot.trade.model.PayOrderModel;
import cn.afterturn.boot.trade.service.IPayOrderService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.afterturn.boot.bussiness.base.service.BaseServiceCacheImpl;
import java.util.List;
import java.util.Map;

/**
 * 支付订单服务实现
 *
 * @author JueYue
 * @Date 2019-08-11 12:49:07
 */
@Service("payOrderService")
public class PayOrderServiceImpl extends BaseServiceCacheImpl<PayOrderRepository, PayOrderModel> implements IPayOrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PayOrderServiceImpl.class);

    @Autowired
    private PayOrderRepository payOrderRepository;

}

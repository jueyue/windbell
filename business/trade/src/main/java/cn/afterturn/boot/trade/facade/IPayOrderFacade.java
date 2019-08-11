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
package cn.afterturn.boot.trade.facade;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * 支付订单接口
 *
 * @author JueYue
 * @Date 2019-08-11 12:49:07
 */
@FeignClient(value = "payOrderFacade")
public interface IPayOrderFacade {

}
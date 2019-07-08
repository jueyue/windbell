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
package cn.afterturn.boot.facade.paas.im;

import cn.afterturn.boot.bussiness.base.controller.IBaseController;
import cn.afterturn.boot.facade.paas.im.model.PaasUserRequestModel;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 三方平台用户信息
 *
 * @author JueYue
 * @Date 2019-06-27 16:40:25
 */
@FeignClient(value = "paasUserFacade", contextId = "lemur-paas", path = "/im/user")
public interface IPaasUserFacade extends IBaseController<PaasUserRequestModel> {

}

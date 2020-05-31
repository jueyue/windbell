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
package com.wupaas.boot.paas.im.service;

import com.wupaas.boot.bussiness.base.service.IBaseService;
import com.wupaas.boot.paas.im.model.AppInfoConfigModel;


/**
 * 三方平台APP信息服务
 *
 * @author JueYue
 * @Date 2019-06-27 16:40:25
 */
public interface IAppInfoConfigService extends IBaseService<AppInfoConfigModel> {
    /**
     * 获取秘钥
     * @param tenantId
     * @param appType
     * @param appCode
     * @return
     */
    AppInfoConfigModel getSecret(String tenantId, String appType, String appCode);
}

        /**
 * Copyright 2017-2018 JueYue (qrb.jueyue@foxmail.com)
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
package com.wupaas.boot.paas.im.controller;

import com.wupaas.boot.core.business.base.controller.BaseController;
import com.wupaas.boot.paas.im.model.AppInfoConfigModel;
import com.wupaas.boot.paas.im.service.IAppInfoConfigService;

import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.wupaas.boot.paas.common.Constant.PAAS;


        /**
 * 三方平台APP信息控制器
 *
 * @author JueYue
 * @Date 2019-06-27 16:40:25
 */
@Api("三方平台APP信息")
@RestController
@RequestMapping(PAAS + "/appinfoconfig")
public class AppInfoConfigController extends BaseController<IAppInfoConfigService, AppInfoConfigModel> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppInfoConfigController.class);

    @Autowired
    private IAppInfoConfigService appInfoConfigService;

}

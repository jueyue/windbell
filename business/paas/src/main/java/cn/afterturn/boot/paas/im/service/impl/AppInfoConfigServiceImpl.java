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
package cn.afterturn.boot.paas.im.service.impl;

import cn.afterturn.boot.paas.im.repository.AppInfoConfigRepository;
import cn.afterturn.boot.paas.im.model.AppInfoConfigModel;
import cn.afterturn.boot.paas.im.service.IAppInfoConfigService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.afterturn.boot.bussiness.base.service.BaseServiceCacheImpl;
import java.util.List;
import java.util.Map;

/**
 * 三方平台APP信息服务实现
 *
 * @author JueYue
 * @Date 2019-06-27 16:40:25
 */
@Service("appInfoConfigService")
public class AppInfoConfigServiceImpl extends BaseServiceCacheImpl<AppInfoConfigRepository, AppInfoConfigModel> implements IAppInfoConfigService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppInfoConfigServiceImpl.class);

    @Autowired
    private AppInfoConfigRepository appInfoConfigRepository;

}

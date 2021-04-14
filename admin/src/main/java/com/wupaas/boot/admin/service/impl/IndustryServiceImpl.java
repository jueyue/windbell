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
package com.wupaas.boot.admin.service.impl;

import com.wupaas.boot.admin.model.IndustryModel;
import com.wupaas.boot.admin.repository.IndustryRepository;
import com.wupaas.boot.admin.service.IIndustryService;
import com.wupaas.boot.core.business.base.service.BaseServiceCacheImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 行业信息表服务实现
 *
 * @author JueYue
 * @Date 2018-11-12 15:52:05
 */
@Service("industryService")
public class IndustryServiceImpl extends BaseServiceCacheImpl<IndustryRepository, IndustryModel> implements IIndustryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndustryServiceImpl.class);

    @Autowired
    private IndustryRepository industryRepository;

}

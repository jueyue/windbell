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
package cn.afterturn.boot.admin.service.impl;

import cn.afterturn.boot.admin.model.LinkTenantProductModel;
import cn.afterturn.boot.admin.repository.LinkTenantProductRepository;
import cn.afterturn.boot.admin.service.ILinkTenantProductService;
import cn.afterturn.boot.bussiness.base.service.BaseServiceCacheImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 租户开通产品信息服务实现
 *
 * @author JueYue
 * @Date 2018-09-18 15:34:41
 */
@Service("linkTenantProductService")
public class LinkTenantProductServiceImpl extends BaseServiceCacheImpl<LinkTenantProductRepository, LinkTenantProductModel> implements ILinkTenantProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LinkTenantProductServiceImpl.class);

    @Autowired
    private LinkTenantProductRepository linkTenantProductRepository;

    @Override
    public void addOrUpdateProduct(String tenantId, String product, String endDate) {
        // TODO
    }

}

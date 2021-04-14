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
package com.wupaas.boot.admin.service.impl;

import com.wupaas.boot.admin.repository.LinkUserRoleRepository;
import com.wupaas.boot.admin.model.LinkUserRoleModel;
import com.wupaas.boot.admin.service.ILinkUserRoleService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wupaas.boot.core.business.base.service.BaseServiceCacheImpl;

        /**
 * 角色和用户关联表服务实现
 *
 * @author JueYue
 * @Date 2018-11-13 15:07:54
 */
@Service("linkUserRoleService")
public class LinkUserRoleServiceImpl extends BaseServiceCacheImpl<LinkUserRoleRepository, LinkUserRoleModel> implements ILinkUserRoleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LinkUserRoleServiceImpl.class);

    @Autowired
    private LinkUserRoleRepository linkUserRoleRepository;

}

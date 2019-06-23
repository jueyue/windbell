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

import cn.afterturn.boot.admin.model.LinkRoleMenuModel;
import cn.afterturn.boot.admin.repository.LinkRoleMenuRepository;
import cn.afterturn.boot.admin.service.ILinkRoleMenuService;
import cn.afterturn.boot.bussiness.base.service.BaseServiceCacheImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 角色和菜单关联表服务实现
 *
 * @author JueYue
 * @Date 2019-06-02 22:08:04
 */
@Service("linkRoleMenuService")
public class LinkRoleMenuServiceImpl extends BaseServiceCacheImpl<LinkRoleMenuRepository, LinkRoleMenuModel> implements ILinkRoleMenuService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LinkRoleMenuServiceImpl.class);

    @Autowired
    private LinkRoleMenuRepository linkRoleMenuRepository;

}

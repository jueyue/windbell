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

import com.wupaas.boot.admin.model.LinkRoleMenuModel;
import com.wupaas.boot.admin.model.RoleModel;
import com.wupaas.boot.admin.repository.RoleRepository;
import com.wupaas.boot.admin.service.ILinkRoleMenuService;
import com.wupaas.boot.admin.service.IRoleService;
import com.wupaas.boot.core.business.base.service.BaseServiceCacheImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色表服务实现
 *
 * @author JueYue
 * @Date 2018-09-05 16:35:32
 */
@Service("roleService")
public class RoleServiceImpl extends BaseServiceCacheImpl<RoleRepository, RoleModel> implements IRoleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    private RoleRepository       roleRepository;
    @Autowired
    private ILinkRoleMenuService linkRoleMenuService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveAuth(String roleId, List<String> menuIds) {
        List<LinkRoleMenuModel> linkList = new ArrayList<>();
        for (int i = 0; i < menuIds.size(); i++) {
            linkList.add(new LinkRoleMenuModel(menuIds.get(i), roleId));
        }
        linkRoleMenuService.remove(new QueryWrapper<LinkRoleMenuModel>().eq("ROLE_ID", roleId));
        linkRoleMenuService.saveBatch(linkList);
    }
}

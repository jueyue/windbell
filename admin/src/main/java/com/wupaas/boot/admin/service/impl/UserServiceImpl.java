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

import com.wupaas.boot.admin.model.LinkUserRoleModel;
import com.wupaas.boot.admin.model.UserAuthModel;
import com.wupaas.boot.admin.model.UserModel;
import com.wupaas.boot.admin.repository.UserRepository;
import com.wupaas.boot.admin.service.ILinkUserRoleService;
import com.wupaas.boot.admin.service.IUserAuthService;
import com.wupaas.boot.admin.service.IUserService;
import com.wupaas.boot.bussiness.base.service.BaseServiceCacheImpl;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户管理服务实现
 *
 * @author JueYue
 * @Date 2018-09-06 20:36:08
 */
@Service("userService")
public class UserServiceImpl extends BaseServiceCacheImpl<UserRepository, UserModel> implements IUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository       userRepository;
    @Autowired
    private ILinkUserRoleService linkUserRoleService;
    @Autowired
    private IUserAuthService     userAuthService;

    @Override
    protected boolean handlerSave(UserModel model) {
        //同步角色
        if (!insertUserRole(model.getAccess(), model.getId())) {
            return false;
        }
        // 同步auth数据
        userAuthService.save(getUserAuth(model));
        //同步三方数据
//        if (!paasUserFacade.create(getPaasUserRequestModel(model)).isSuccess()) {
//            return false;
//        }
        return true;
    }

    private UserAuthModel getUserAuth(UserModel model) {
        UserAuthModel authModel = new UserAuthModel();
        authModel.setAccount(model.getAccount());
        authModel.setAvatar(model.getAvatar());
        authModel.setName(model.getName());
        authModel.setUserId(model.getId());
        authModel.setType(1);
        authModel.setPassword(model.getPassword());
        authModel.setTenantId(model.getTenantId());
        return authModel;
    }

//    private PaasUserRequestModel getPaasUserRequestModel(UserModel model) {
//        PaasUserRequestModel paasUserRequestModel = new PaasUserRequestModel();
//        BeanUtils.copyProperties(model, paasUserRequestModel);
//        paasUserRequestModel.setAppCode(AppIdEnum.CONTACTS.getAppId());
//        return paasUserRequestModel;
//    }

    @Override
    protected boolean handlerUpdateById(UserModel model) {
        //同步角色
        linkUserRoleService.remove(new QueryWrapper<LinkUserRoleModel>().eq("USER_ID", model.getId()));
        if (!insertUserRole(model.getAccess(), model.getId())) {
            return false;
        }
        return true;
    }


    private boolean insertUserRole(String access, String userId) {
        if (StringUtils.isNotEmpty(access)) {
            List<String>            roleIds = JSON.parseArray(access, String.class);
            List<LinkUserRoleModel> list    = new ArrayList<>(roleIds.size());
            for (int i = 0; i < roleIds.size(); i++) {
                list.add(new LinkUserRoleModel(userId, roleIds.get(i)));
            }
            return linkUserRoleService.saveBatch(list);

        }
        return true;
    }

    @Override
    public List<UserModel> getUserByRole(String roleName, String tenantId) {
        return userRepository.getUserByRole(roleName,tenantId);
    }
}

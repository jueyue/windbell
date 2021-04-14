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
package com.wupaas.boot.admin.service;

import com.wupaas.boot.admin.model.UserModel;
import com.wupaas.boot.core.business.base.service.IBaseService;

import java.util.List;


/**
 * 用户管理服务
 *
 * @author JueYue
 * @Date 2018-09-06 20:36:08
 */
public interface IUserService extends IBaseService<UserModel> {
    /**
     * 获取某个角色下的用户
     * @param roleName
     * @param tenantId
     * @return
     */
    List<UserModel> getUserByRole(String roleName, String tenantId);
}


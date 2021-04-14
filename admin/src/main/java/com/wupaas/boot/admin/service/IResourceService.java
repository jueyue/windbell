/**
 * Copyright 2017-2019 JueYue (qrb.jueyue@foxmail.com)
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

import com.wupaas.boot.admin.model.ResourceModel;
import com.wupaas.boot.core.business.base.service.IBaseService;
import com.wupaas.boot.core.web.iview.IViewTree;

import java.util.List;


/**
 * 资源管理服务
 *
 * @author JueYue
 * @Date 2019-07-06 17:09:00
 */
public interface IResourceService extends IBaseService<ResourceModel> {

    /**
     * 根据用户ID,获取用户的所有权限
     *
     * @param userId
     * @param webType
     * @return
     */
    List<String> getAllByUserId(String userId, String webType);

    /**
     * 更加菜单查询资源
     *
     * @param menuId
     * @return
     */
    List<IViewTree> getTreeByMenuId(String menuId);

    /**
     * 保存菜单关联资源
     *
     * @param menuId
     * @param resourceIds
     */
    boolean saveAuth(String menuId, List<String> resourceIds);
}

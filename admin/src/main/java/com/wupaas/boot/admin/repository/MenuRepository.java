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
package com.wupaas.boot.admin.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wupaas.boot.admin.model.MenuModel;
import com.wupaas.boot.web.iview.IViewMenu;
import com.wupaas.boot.web.iview.IViewTree;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Menu持久化
 *
 * @author JueYue
 * @Date 2018-10-12 14:51:42
 */
@Repository
public interface MenuRepository extends BaseMapper<MenuModel> {

    /**
     * 查询角色的菜单
     *
     * @param roleId
     * @param pid
     * @return
     */
    List<IViewTree> getMenuByRoleId(@Param("roleId") String roleId, @Param("pid") String pid);

    /**
     * 获取用户的所有权限
     *
     * @param userId
     * @param webType
     * @return
     */
    List<String> getAllByUserId(@Param("userId") String userId, @Param("webType") String webType);

    /**
     * 查询用户的菜单
     *
     * @param userId
     * @param webType
     * @param pid
     * @return
     */
    List<IViewMenu> getMenuByUserId(@Param("userId") String userId,@Param("webType") String webType, @Param("pid") String pid);
}

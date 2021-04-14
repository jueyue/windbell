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
package com.wupaas.boot.admin.repository;

import com.wupaas.boot.admin.model.ResourceModel;
import com.wupaas.boot.web.iview.IViewTree;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * Resource持久化
 *
 * @author JueYue
 * @Date 2019-07-06 17:09:00
 */
@Repository
public interface ResourceRepository extends BaseMapper<ResourceModel> {

    /**
     * 获取用户的所有权限
     * @param userId
     * @param webType
     * @return
     */
    List<String> getAllByUserId(@Param("userId") String userId, @Param("webType") String webType);

    /**
     * 查询资源树
     * @param menuId
     * @param pid
     * @return
     */
    List<IViewTree> getTreeByMenuId(@Param("menuId") String menuId, @Param("pid") String pid);

    /**
     * 查询用户的资源权限
     * @param user
     * @return
     */
    Set<String> getResourceByUser(@Param("userName") String user);

    Set<String> getNotNeedPermissions();
}

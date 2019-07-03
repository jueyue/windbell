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
package cn.afterturn.boot.admin.service;

import cn.afterturn.boot.admin.model.RoleModel;
import cn.afterturn.boot.bussiness.base.service.IBaseService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.List;


/**
 * 角色表服务
 *
 * @author JueYue
 * @Date 2018-09-05 16:35:32
 */
public interface IRoleService extends IBaseService<RoleModel> {

    /**
     * 保存权限
     * @param roleId
     * @param menuIds
     */
    void saveAuth(String roleId, List<String> menuIds);

    List<RoleModel> tree(QueryWrapper wrapper);
}

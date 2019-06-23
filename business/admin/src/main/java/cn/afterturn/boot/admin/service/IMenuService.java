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

import cn.afterturn.boot.admin.model.MenuModel;
import cn.afterturn.boot.bussiness.base.service.IBaseService;
import cn.afterturn.boot.web.iview.IViewTree;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.List;


/**
 * 菜单表服务
 *
 * @author JueYue
 * @Date 2018-10-12 14:51:42
 */
public interface IMenuService extends IBaseService<MenuModel> {

    /**
     * 获取树状的菜单
     *
     * @param wrapper
     * @return
     */
    List<MenuModel> tree(QueryWrapper wrapper);

    /**
     * 根据角色ID,查询菜单
     * @param roleId
     * @return
     */
    List<IViewTree> getTreeByRoleId(String roleId);
}

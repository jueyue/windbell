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

import cn.afterturn.boot.admin.model.MenuModel;
import cn.afterturn.boot.admin.repository.MenuRepository;
import cn.afterturn.boot.admin.service.IMenuService;
import cn.afterturn.boot.bussiness.base.service.BaseServiceCacheImpl;
import cn.afterturn.boot.core.support.CollectionKit;
import cn.afterturn.boot.web.iview.IViewTree;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 菜单表服务实现
 *
 * @author JueYue
 * @Date 2018-10-12 14:51:42
 */
@Service("menuService")
public class MenuServiceImpl extends BaseServiceCacheImpl<MenuRepository, MenuModel> implements IMenuService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MenuServiceImpl.class);

    @Autowired
    private MenuRepository menuRepository;

    @Override
    public List<MenuModel> tree(QueryWrapper wrapper) {
        List<MenuModel> list = list(wrapper);
        loadAllSubMenu(list);
        return list;
    }

    private void loadAllSubMenu(List<MenuModel> list) {
        MenuModel    menu    = new MenuModel();
        QueryWrapper wrapper = new QueryWrapper<>(menu);
        wrapper.orderByAsc("NUM");
        for (int i = 0; i < list.size(); i++) {
            menu.setPid(list.get(i).getId());
            List<MenuModel> tempList = list(wrapper);
            if (CollectionKit.isNotEmpty(tempList)) {
                list.get(i).setChildren(tempList);
                loadAllSubMenu(tempList);
            }
        }
    }


    @Override
    public List<IViewTree> getTreeByRoleId(String roleId) {
        List<IViewTree> list = menuRepository.getMenuByRoleId(roleId, null);
        loadAllSubTree(list, roleId);
        return list;
    }

    @Override
    public List<String> getAllByUserId(String userId,String productCode) {
        return menuRepository.getAllByUserId(userId,productCode);
    }

    private void loadAllSubTree(List<IViewTree> list, String roleId) {
        for (int i = 0; i < list.size(); i++) {
            List<IViewTree> tempList = menuRepository.getMenuByRoleId(roleId, list.get(i).getId());
            if (CollectionKit.isNotEmpty(tempList)) {
                list.get(i).setChildren(tempList);
                // 前端设置true会全选
                list.get(i).setChecked(false);
                loadAllSubTree(tempList, roleId);
            }
        }
    }
}

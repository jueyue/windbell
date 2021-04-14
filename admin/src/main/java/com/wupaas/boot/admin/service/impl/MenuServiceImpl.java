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

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wupaas.boot.admin.model.MenuModel;
import com.wupaas.boot.admin.repository.MenuRepository;
import com.wupaas.boot.admin.service.IMenuService;
import com.wupaas.boot.bussiness.base.service.BaseServiceCacheImpl;
import com.wupaas.boot.core.support.CollectionKit;
import com.wupaas.boot.web.iview.IViewMenu;
import com.wupaas.boot.web.iview.IViewTree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
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
    protected boolean handlerSave(MenuModel model) {
        // 作为基础资源生成,一次把几个资源都生成完
        if (model.getBase() != null && model.getBase() == 1) {
            copyInsertModel("新增", "_create", model, model.getNum() + 1);
            copyInsertModel("修改", "_update", model, model.getNum() + 2);
            copyInsertModel("删除", "_delete", model, model.getNum() + 3);
            return true;
        } else {
            return super.handlerSave(model);
        }
    }

    /**
     * 生成 新增,修改,删除
     *
     * @param name
     * @param code
     * @param model
     */
    private void copyInsertModel(String name, String code, MenuModel model, int num) {
        MenuModel newModel = new MenuModel();
        BeanUtils.copyProperties(model, newModel);
        newModel.setPid(model.getId());
        newModel.setId(null);
        newModel.setNum(num);
        newModel.setName(name);
        newModel.setIsmenu("2");
        newModel.setCode(model.getCode() + code);
        menuRepository.insert(newModel);
    }

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
    public List<String> getAllByUserId(String userId, String webType) {
        return menuRepository.getAllByUserId(userId, webType);
    }

    @Override
    public List<IViewMenu> getMenuTreeByUserId(String webType, String userId) {
        List<IViewMenu> list = menuRepository.getMenuByUserId(userId, webType, null);
        loadAllMenuSubTree(list, webType, userId);
        return list;
    }

    private void loadAllMenuSubTree(List<IViewMenu> list, String webType, String userId) {
        list.forEach(menu -> {
            List<IViewMenu> tempList = menuRepository.getMenuByUserId(userId, webType, menu.getId());
            if (CollectionKit.isNotEmpty(tempList)) {
                menu.setChildren(tempList);
                loadAllMenuSubTree(tempList, webType, userId);
            }
        });
    }

    private void loadAllSubTree(List<IViewTree> list, String roleId) {
        list.forEach(tree -> {
            List<IViewTree> tempList = menuRepository.getMenuByRoleId(roleId, tree.getId());
            if (CollectionKit.isNotEmpty(tempList)) {
                tree.setChildren(tempList);
                // 前端设置true会全选
                tree.setChecked(false);
                loadAllSubTree(tempList, roleId);
            }
        });
    }
}

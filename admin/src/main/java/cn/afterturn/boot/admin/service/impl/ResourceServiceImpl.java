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
package cn.afterturn.boot.admin.service.impl;

import cn.afterturn.boot.admin.model.LinkMenuResourceModel;
import cn.afterturn.boot.admin.model.ResourceModel;
import cn.afterturn.boot.admin.repository.ResourceRepository;
import cn.afterturn.boot.admin.service.ILinkMenuResourceService;
import cn.afterturn.boot.admin.service.IResourceService;
import cn.afterturn.boot.bussiness.base.service.BaseServiceCacheImpl;
import cn.afterturn.boot.core.support.CollectionKit;
import cn.afterturn.boot.web.iview.IViewTree;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 资源管理服务实现
 *
 * @author JueYue
 * @Date 2019-07-06 17:09:00
 */
@Service("resourceService")
public class ResourceServiceImpl extends BaseServiceCacheImpl<ResourceRepository, ResourceModel> implements IResourceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceServiceImpl.class);

    @Autowired
    private ResourceRepository       resourceRepository;
    @Autowired
    private ILinkMenuResourceService linkMenuResourceService;

    @Override
    protected boolean handlerSave(ResourceModel model) {
        // 作为基础资源生成,一次把几个资源都生成完
        if (model.getBase() != null && model.getBase() == 1) {
            copyInsertModel("新增", "create", model, model.getNum() + 1);
            copyInsertModel("修改", "update", model, model.getNum() + 2);
            copyInsertModel("删除", "delete", model, model.getNum() + 3);
            copyInsertModel("批量删除", "deleteBatchIds", model, model.getNum() + 4);
            copyInsertModel("详情", "detail", model, model.getNum() + 5);
            return true;
        } else {
            return super.handlerSave(model);
        }
    }

    /**
     * 生成 新增,修改,删除
     *
     * @param name
     * @param url
     * @param model
     */
    private void copyInsertModel(String name, String url, ResourceModel model, int num) {
        ResourceModel newModel = new ResourceModel();
        BeanUtils.copyProperties(model, newModel);
        newModel.setPid(model.getId());
        newModel.setId(null);
        newModel.setNum(num);
        newModel.setName(model.getName().replace("管理", name));
        newModel.setUrl(model.getUrl().replace("list", url));
        resourceRepository.insert(newModel);
    }

    @Override
    public List<String> getAllByUserId(String userId, String productCode) {
        return resourceRepository.getAllByUserId(userId, productCode);
    }

    @Override
    public List<IViewTree> getTreeByMenuId(String menuId) {
        List<IViewTree> list = resourceRepository.getTreeByMenuId(menuId, null);
        loadAllSubTree(list, menuId);
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveAuth(String menuId, List<String> resourceIds) {
        List<LinkMenuResourceModel> linkList = new ArrayList<>();
        for (int i = 0; i < resourceIds.size(); i++) {
            linkList.add(new LinkMenuResourceModel(resourceIds.get(i), menuId));
        }
        linkMenuResourceService.remove(new QueryWrapper<LinkMenuResourceModel>().eq("MENU_ID", menuId));
        return linkMenuResourceService.saveBatch(linkList);
    }

    private void loadAllSubTree(List<IViewTree> list, String menuId) {
        for (int i = 0; i < list.size(); i++) {
            List<IViewTree> tempList = resourceRepository.getTreeByMenuId(menuId, list.get(i).getId());
            if (CollectionKit.isNotEmpty(tempList)) {
                list.get(i).setChildren(tempList);
                loadAllSubTree(tempList, menuId);
            }
        }
    }

}

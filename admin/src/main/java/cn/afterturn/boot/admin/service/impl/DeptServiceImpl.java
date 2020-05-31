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

import cn.afterturn.boot.admin.model.DeptModel;
import cn.afterturn.boot.admin.model.LinkDeptDataAuthModel;
import cn.afterturn.boot.admin.repository.DeptRepository;
import cn.afterturn.boot.admin.service.IDeptService;
import cn.afterturn.boot.admin.service.ILinkDeptDataAuthService;
import cn.afterturn.boot.bussiness.base.service.BaseServiceCacheImpl;
import cn.afterturn.boot.core.support.CollectionKit;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 部门表服务实现
 *
 * @author JueYue
 * @Date 2018-09-18 15:25:41
 */
@Service("deptService")
public class DeptServiceImpl extends BaseServiceCacheImpl<DeptRepository, DeptModel> implements IDeptService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeptServiceImpl.class);

    @Autowired
    private ILinkDeptDataAuthService linkDeptDataAuthService;
//    @Autowired
//    private IPaasDeptFacade          paasDeptFacade;

    @Override
    protected boolean handlerSave(DeptModel model) {
        // 插入默认数据关联,默认数据权限包含下级
        //需要调用微信和钉钉同步企业信息
        // paasDeptFacade.create(getPaasDeptModel(model)).isSuccess()
        //                &&
        return linkDeptDataAuthService.save(new LinkDeptDataAuthModel(model.getId(), model.getId(), 1));
    }

    @Override
    public List<DeptModel> tree(QueryWrapper wrapper) {
        List<DeptModel> list = list(wrapper);
        loadAllSubDept(list);
        return list;
    }

    @Override
    public void batchToPaas(List<DeptModel> list) {
        list.forEach(deptModel -> {
            //paasDeptFacade.create(getPaasDeptModel(deptModel));
            if (deptModel.getChildren() != null) {
                batchToPaas(deptModel.getChildren());
            }
        });
    }

//    private PaasDeptRequestModel getPaasDeptModel(DeptModel deptModel) {
//        PaasDeptRequestModel deptRequestModel = new PaasDeptRequestModel();
//        BeanUtils.copyProperties(deptModel, deptRequestModel);
//        deptRequestModel.setAppCode(AppIdEnum.CONTACTS.getAppId());
//        return deptRequestModel;
//    }

    private void loadAllSubDept(List<DeptModel> list) {
        DeptModel dept = new DeptModel();
        for (int i = 0; i < list.size(); i++) {
            dept.setPid(list.get(i).getId());
            List<DeptModel> tempList = list(dept);
            if (CollectionKit.isNotEmpty(tempList)) {
                list.get(i).setChildren(tempList);
                loadAllSubDept(tempList);
            }
        }
    }
}

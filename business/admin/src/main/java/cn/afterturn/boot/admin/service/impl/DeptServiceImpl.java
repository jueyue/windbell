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
import cn.afterturn.boot.admin.model.SequenceModel;
import cn.afterturn.boot.admin.repository.DeptRepository;
import cn.afterturn.boot.admin.service.IDeptService;
import cn.afterturn.boot.admin.service.ILinkDeptDataAuthService;
import cn.afterturn.boot.admin.service.ISequenceService;
import cn.afterturn.boot.bussiness.base.service.BaseServiceCacheImpl;
import cn.afterturn.boot.core.support.CollectionKit;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private DeptRepository deptRepository;
    @Autowired
    private ISequenceService sequenceService;
    @Autowired
    private ILinkDeptDataAuthService linkDeptDataAuthService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(DeptModel entity) {
        if (entity.getPid() == null) {
            entity.setDeptCode(sequenceService.getNext("DEPT_CODE", 6));
        } else {
            DeptModel pdept = deptRepository.selectById(entity.getPid());
            entity.setDeptCode(sequenceService.getNext("DEPT_CODE_" + pdept.getDeptCode(), 4));
        }
        // 插入默认数据关联,默认数据权限包含下级
        if (super.save(entity)
                && linkDeptDataAuthService.save(new LinkDeptDataAuthModel(entity.getId(), entity.getId(), 1))) {
            //TODO 需要调用微信和钉钉同步企业信息
            return true;
        }
        return false;
    }

    @Override
    public List<DeptModel> tree(QueryWrapper wrapper) {
        List<DeptModel> list = list(wrapper);
        loadAllSubDept(list);
        return list;
    }

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

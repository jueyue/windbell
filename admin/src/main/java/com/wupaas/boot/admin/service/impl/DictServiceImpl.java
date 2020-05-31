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

import com.wupaas.boot.admin.model.DictModel;
import com.wupaas.boot.admin.repository.DictRepository;
import com.wupaas.boot.admin.service.IDictService;
import com.wupaas.boot.bussiness.base.service.BaseServiceCacheImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;

/**
 * 字典表服务实现
 *
 * @author JueYue
 * @Date 2018-09-06 20:58:18
 */
@Service("dictService")
public class DictServiceImpl extends BaseServiceCacheImpl<DictRepository, DictModel> implements IDictService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DictServiceImpl.class);

    @Autowired
    private DictRepository dictRepository;

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public boolean save(DictModel entity) {
        boolean insertSuccess = retBool(this.dictRepository.insert(entity));
        if (insertSuccess) {
            for (int i = 0; i < entity.getSubDict().size(); i++) {
                entity.getSubDict().get(i).setPid(entity.getId());
                entity.getSubDict().get(i).setPkey(entity.getKey());
                insertSuccess = retBool(this.dictRepository.insert(entity.getSubDict().get(i)));
            }
        }
        return insertSuccess;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    @CacheEvict(key = "#root.targetClass + ':key_'+#key")
    public boolean updateById(DictModel entity) {
        boolean updateSuccess = retBool(dictRepository.updateById(entity));
        if (updateSuccess) {
            dictRepository.delete(new QueryWrapper<DictModel>().eq("P_ID", entity.getId()));
            for (int i = 0; i < entity.getSubDict().size(); i++) {
                entity.getSubDict().get(i).setPid(entity.getId());
                entity.getSubDict().get(i).setPkey(entity.getKey());
            }
            updateSuccess = saveBatch(entity.getSubDict());
        }
        return updateSuccess;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    @CacheEvict(allEntries = true)
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        for (Serializable id : idList) {
            dictRepository.delete(new QueryWrapper<DictModel>().eq("P_ID", id));
        }
        return super.removeByIds(idList);
    }


    @Override
    @Cacheable(key = "#root.targetClass + ':key_'+#key")
    public DictModel getById(Serializable key) {
        DictModel model = getOne(new QueryWrapper<DictModel>().eq("DICT_KEY", key));
        DictModel temp = new DictModel();
        temp.setPid(model.getId());
        model.setSubDict(this.list(temp));
        return model;
    }


}

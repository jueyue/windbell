package com.wupaas.boot.core.business.base.service;

import com.wupaas.boot.core.common.exception.BootException;
import com.wupaas.boot.core.common.exception.BootExceptionEnum;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 带有缓存服务类
 *
 * @author by jueyue on 18-6-26.
 */
@CacheConfig(cacheNames = "baseCache")
public abstract class BaseServiceCacheImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements IBaseService<T> {

    @Autowired
    protected M baseMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean save(T model) {
        boolean bool = super.save(model);
        if (bool) {
            if (!handlerSave(model)) {
                throw new BootException(BootExceptionEnum.DATA_SAVE_ERROR);
            }
        }
        return bool;
    }

    /**
     * 处理新增相关处理
     * @param model
     * @return
     */
    protected boolean handlerSave(T model){
        return true;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    @CacheEvict(key = "#root.targetClass + ':id_'+#id")
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    @CacheEvict(allEntries = true)
    public boolean removeByMap(Map columnMap) {
        return super.removeByMap(columnMap);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    @CacheEvict(allEntries = true)
    public boolean remove(Wrapper wrapper) {
        return super.remove(wrapper);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    @CacheEvict(allEntries = true)
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        return super.removeByIds(idList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    @CacheEvict(key = "#root.targetClass + ':id_'+ #p0.id")
    public boolean updateById(T model) {
        boolean bool = super.updateById(model);
        if (bool) {
            if (!handlerUpdateById(model)) {
                throw new BootException(BootExceptionEnum.DATA_UPDATE_ERROR);
            }
        }
        return bool;
    }

    /**
     * 处理修改相关处理
     * @param model
     * @return
     */
    protected boolean handlerUpdateById(T model){
        return true;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    @CacheEvict(allEntries = true)
    public boolean update(T model, Wrapper<T> wrapper) {
        return super.update(model, wrapper);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    @CacheEvict(allEntries = true)
    public boolean updateBatchById(Collection<T> modelList) {
        return super.updateBatchById(modelList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    @CacheEvict(allEntries = true)
    public boolean updateBatchById(Collection<T> modelList, int batchSize) {
        return super.updateBatchById(modelList, batchSize);
    }

    @Override
    @Cacheable(key = "#root.targetClass + ':id_'+#id")
    public T getById(Serializable id) {
        return super.getById(id);
    }

    @Override
    public T getOne(T model) {
        return baseMapper.selectOne(new QueryWrapper<T>(model));
    }

    @Override
    public List<T> list(T model) {

        return baseMapper.selectList(new QueryWrapper<>(model));
    }

}

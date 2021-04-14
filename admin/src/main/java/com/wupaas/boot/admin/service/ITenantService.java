package com.wupaas.boot.admin.service;

import com.wupaas.boot.admin.model.TenantModel;
import com.wupaas.boot.core.business.base.service.IBaseService;


/**
 * 服务
 *
 * @author
 * @Date 2018-09-03 23:21:00
 */
public interface ITenantService extends IBaseService<TenantModel> {

    /**
     * 平台初始化
     *  目前只新增,不删除
     * @param id
     * @return 是否初始化成功
     */
    boolean paasInit(String id);
}

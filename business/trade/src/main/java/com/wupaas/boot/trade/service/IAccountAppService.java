package com.wupaas.boot.trade.service;

import com.wupaas.boot.bussiness.base.service.IBaseService;
import com.wupaas.boot.trade.model.AccountAppModel;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;


/**
 * Service
 * 应用
 *
 * @author
 * @Date
 */
public interface IAccountAppService extends IBaseService<AccountAppModel> {

    /**
     * <p>
     * 根据 model 条件，查询一条记录
     * </p>
     *
     * @param model 实体对象 非空
     * @return AccountAppModel
     */
    AccountAppModel selectOne(AccountAppModel model);

    /**
     * <p>
     * 根据 model 条件，查询全部记录
     * </p>
     *
     * @param model 实体对象封装操作类（可以为 null）
     * @return List<AccountAppModel>
     */
    List<AccountAppModel> selectList(AccountAppModel model);

    /**
     * <p>
     * 根据 model 条件，查询全部记录
     * </p>
     *
     * @param model   实体对象封装操作类（可以为 null）
     * @param wrapper SQL包装
     * @return List<AccountAppModel>
     */
    List<AccountAppModel> selectList(AccountAppModel model, Wrapper<AccountAppModel> wrapper);


    /**
     * <p>
     * 根据 model 条件，查询全部记录（并翻页）
     * </p>
     *
     * @param pagination 分页查询条件
     * @param model      实体对象封装操作可以为 null）
     * @param wrapper    SQL包装
     * @return List<AccountAppModel>
     */
    List<AccountAppModel> selectPage(Page pagination, AccountAppModel model, Wrapper<AccountAppModel> wrapper);

    /**
     * 新增APP应用
     *
     * @param model
     */
    void add(AccountAppModel model);
}

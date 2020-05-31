package com.wupaas.boot.trade.service;

import com.wupaas.boot.bussiness.base.service.IBaseService;
import com.wupaas.boot.trade.model.AccountSubjectModel;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;


/**
 * Service
 * 账户科目表
 *
 * @author
 * @Date
 */
public interface IAccountSubjectService extends IBaseService<AccountSubjectModel> {

    /**
     * <p>
     * 根据 model 条件，查询一条记录
     * </p>
     *
     * @param model 实体对象 非空
     * @return AccountSubjectModel
     */
    AccountSubjectModel selectOne(AccountSubjectModel model);

    /**
     * <p>
     * 根据 model 条件，查询全部记录
     * </p>
     *
     * @param model 实体对象封装操作类（可以为 null）
     * @return List<AccountSubjectModel>
     */
    List<AccountSubjectModel> selectList(AccountSubjectModel model);

    /**
     * <p>
     * 根据 model 条件，查询全部记录
     * </p>
     *
     * @param model   实体对象封装操作类（可以为 null）
     * @param wrapper SQL包装
     * @return List<AccountSubjectModel>
     */
    List<AccountSubjectModel> selectList(AccountSubjectModel model, Wrapper<AccountSubjectModel> wrapper);


    /**
     * <p>
     * 根据 model 条件，查询全部记录（并翻页）
     * </p>
     *
     * @param pagination 分页查询条件
     * @param model      实体对象封装操作可以为 null）
     * @param wrapper    SQL包装
     * @return List<AccountSubjectModel>
     */
    List<AccountSubjectModel> selectPage(Page pagination, AccountSubjectModel model, Wrapper<AccountSubjectModel> wrapper);

}

        
package com.wupaas.boot.trade.service;

import com.wupaas.boot.core.business.base.service.IBaseService;
import com.wupaas.boot.trade.model.AccountBalanceCalcModel;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;


/**
 * 总分平衡Service
 * 总分平衡
 * @author JueYue
 * @Date 2018-09-12 19:47:54
 */
public interface IAccountBalanceCalcService extends IBaseService<AccountBalanceCalcModel> {

    /**
     * <p>
     * 根据 model 条件，查询一条记录
     * </p>
     *
     * @param model 实体对象 非空
     * @return AccountBalanceCalcModel
     */
    AccountBalanceCalcModel selectOne(AccountBalanceCalcModel model);
    /**
     * <p>
     * 根据 model 条件，查询全部记录
     * </p>
     *
     * @param model 实体对象封装操作类（可以为 null）
     * @return List<AccountBalanceCalcModel>
     */
    List<AccountBalanceCalcModel> selectList(AccountBalanceCalcModel model);

        /**
         * <p>
         * 根据 model 条件，查询全部记录
         * </p>
         *
         * @param model 实体对象封装操作类（可以为 null）
         * @param wrapper   SQL包装
         * @return List<AccountBalanceCalcModel>
         */
    List<AccountBalanceCalcModel> selectList(AccountBalanceCalcModel model, Wrapper<AccountBalanceCalcModel> wrapper);


    /**
     * <p>
     * 根据 model 条件，查询全部记录（并翻页）
     * </p>
     *
     * @param pagination 分页查询条件
     * @param model   实体对象封装操作可以为 null）
     * @param wrapper   SQL包装
     * @return List<AccountBalanceCalcModel>
     */
    List<AccountBalanceCalcModel> selectPage(Page pagination, AccountBalanceCalcModel model, Wrapper<AccountBalanceCalcModel> wrapper);

}

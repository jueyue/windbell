
package com.wupaas.boot.trade.service.impl;

import com.wupaas.boot.core.business.base.service.BaseServiceCacheImpl;
import com.wupaas.boot.trade.repository.AccountBalanceCalcRepository;
import com.wupaas.boot.trade.model.AccountBalanceCalcModel;
import com.wupaas.boot.trade.service.IAccountBalanceCalcService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 总分平衡Service
 * 总分平衡
 *
 * @author JueYue
 * @Date 2018-09-12 19:47:54
 */
@Service
public class AccountBalanceCalcServiceImpl extends BaseServiceCacheImpl<AccountBalanceCalcRepository, AccountBalanceCalcModel> implements IAccountBalanceCalcService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountBalanceCalcServiceImpl.class);

    @Autowired
    private AccountBalanceCalcRepository accountBalanceCalcRepository;


    @Override
    public AccountBalanceCalcModel selectOne(AccountBalanceCalcModel entity) {
        return accountBalanceCalcRepository.selectOne(new QueryWrapper<>(entity));
    }

    @Override
    public List<AccountBalanceCalcModel> selectList(AccountBalanceCalcModel model) {
        return accountBalanceCalcRepository.selectList(model, new QueryWrapper<>());
    }

    @Override
    public List<AccountBalanceCalcModel> selectList(AccountBalanceCalcModel model, Wrapper<AccountBalanceCalcModel> wrapper) {
        return accountBalanceCalcRepository.selectList(model, wrapper);
    }

    @Override
    public List<AccountBalanceCalcModel> selectPage(Page pagination, AccountBalanceCalcModel model, Wrapper<AccountBalanceCalcModel> wrapper) {
        return accountBalanceCalcRepository.selectPage(pagination, model, wrapper);
    }

}

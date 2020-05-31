package com.wupaas.boot.trade.service.impl;

import com.wupaas.boot.bussiness.base.service.BaseServiceCacheImpl;
import com.wupaas.boot.trade.common.util.SerialNumberUtil;
import com.wupaas.boot.trade.repository.AccountAppRepository;
import com.wupaas.boot.trade.model.AccountAppModel;
import com.wupaas.boot.trade.model.AccountInfoModel;
import com.wupaas.boot.trade.service.IAccountAppService;
import com.wupaas.boot.trade.service.IAccountInfoService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service
 * 应用
 *
 * @author
 * @Date
 */
@Service
public class AccountAppServiceImpl extends BaseServiceCacheImpl<AccountAppRepository, AccountAppModel> implements IAccountAppService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountAppServiceImpl.class);

    @Autowired
    private AccountAppRepository accountAppRepository;
    @Autowired
    private IAccountInfoService  accountInfoService;


    @Override
    public AccountAppModel selectOne(AccountAppModel model) {
        return accountAppRepository.selectOne(new QueryWrapper<>(model));
    }

    @Override
    public List<AccountAppModel> selectList(AccountAppModel model) {
        return accountAppRepository.selectList(model, new QueryWrapper<>(model));
    }

    @Override
    public List<AccountAppModel> selectList(AccountAppModel model, Wrapper<AccountAppModel> wrapper) {
        return accountAppRepository.selectList(model, wrapper);
    }

    @Override
    public List<AccountAppModel> selectPage(Page pagination, AccountAppModel model, Wrapper<AccountAppModel> wrapper) {
        return accountAppRepository.selectPage(pagination, model, wrapper);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void add(AccountAppModel model) {
        model.setAppId(SerialNumberUtil.getAppIdSerialNo());
        this.save(model);
        AccountInfoModel account = accountInfoService.addAccount(null, model.getAppId(), null, null);
        model.setAccountNo(account.getAccountNo());
        this.updateById(model);
    }

}

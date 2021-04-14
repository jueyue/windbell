package com.wupaas.boot.trade.service.impl;

import com.wupaas.boot.core.business.base.service.BaseServiceCacheImpl;
import com.wupaas.boot.trade.repository.AccountSubjectRepository;
import com.wupaas.boot.trade.model.AccountSubjectModel;
import com.wupaas.boot.trade.service.IAccountSubjectService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service
 * 账户科目表
 *
 * @author
 * @Date
 */
@Service
public class AccountSubjectServiceImpl extends BaseServiceCacheImpl<com.wupaas.boot.trade.repository.AccountSubjectRepository, AccountSubjectModel> implements IAccountSubjectService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountSubjectServiceImpl.class);

    @Autowired
    private AccountSubjectRepository AccountSubjectRepository;


    @Override
    public AccountSubjectModel selectOne(AccountSubjectModel model) {
        return AccountSubjectRepository.selectOne(new QueryWrapper<>(model));
    }

    @Override
    public List<AccountSubjectModel> selectList(AccountSubjectModel model) {
        return AccountSubjectRepository.selectList(model, new QueryWrapper<>());
    }

    @Override
    public List<AccountSubjectModel> selectList(AccountSubjectModel model, Wrapper<AccountSubjectModel> wrapper) {
        return AccountSubjectRepository.selectList(model, wrapper);
    }

    @Override
    public List<AccountSubjectModel> selectPage(Page pagination, AccountSubjectModel model, Wrapper<AccountSubjectModel> wrapper) {
        return AccountSubjectRepository.selectPage(pagination, model, wrapper);
    }

}

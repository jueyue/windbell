package cn.afterturn.boot.trade.service.impl;

import cn.afterturn.boot.bussiness.base.service.BaseServiceCacheImpl;
import cn.afterturn.boot.trade.dao.AccountSubjectDao;
import cn.afterturn.boot.trade.model.AccountSubjectModel;
import cn.afterturn.boot.trade.service.IAccountSubjectService;
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
public class AccountSubjectServiceImpl extends BaseServiceCacheImpl<AccountSubjectDao, AccountSubjectModel> implements IAccountSubjectService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountSubjectServiceImpl.class);

    @Autowired
    private AccountSubjectDao AccountSubjectDao;


    @Override
    public AccountSubjectModel selectOne(AccountSubjectModel model) {
        return AccountSubjectDao.selectOne(new QueryWrapper<>(model));
    }

    @Override
    public List<AccountSubjectModel> selectList(AccountSubjectModel model) {
        return AccountSubjectDao.selectList(model, new QueryWrapper<>());
    }

    @Override
    public List<AccountSubjectModel> selectList(AccountSubjectModel model, Wrapper<AccountSubjectModel> wrapper) {
        return AccountSubjectDao.selectList(model, wrapper);
    }

    @Override
    public List<AccountSubjectModel> selectPage(Page pagination, AccountSubjectModel model, Wrapper<AccountSubjectModel> wrapper) {
        return AccountSubjectDao.selectPage(pagination, model, wrapper);
    }

}

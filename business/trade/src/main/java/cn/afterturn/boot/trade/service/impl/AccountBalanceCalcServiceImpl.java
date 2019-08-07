
package cn.afterturn.boot.trade.service.impl;

import cn.afterturn.boot.bussiness.base.service.BaseServiceCacheImpl;
import cn.afterturn.boot.trade.dao.AccountBalanceCalcDao;
import cn.afterturn.boot.trade.model.AccountBalanceCalcModel;
import cn.afterturn.boot.trade.service.IAccountBalanceCalcService;
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
public class AccountBalanceCalcServiceImpl extends BaseServiceCacheImpl<AccountBalanceCalcDao, AccountBalanceCalcModel> implements IAccountBalanceCalcService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountBalanceCalcServiceImpl.class);

    @Autowired
    private AccountBalanceCalcDao accountBalanceCalcDao;


    @Override
    public AccountBalanceCalcModel selectOne(AccountBalanceCalcModel entity) {
        return accountBalanceCalcDao.selectOne(new QueryWrapper<>(entity));
    }

    @Override
    public List<AccountBalanceCalcModel> selectList(AccountBalanceCalcModel model) {
        return accountBalanceCalcDao.selectList(model, new QueryWrapper<>());
    }

    @Override
    public List<AccountBalanceCalcModel> selectList(AccountBalanceCalcModel model, Wrapper<AccountBalanceCalcModel> wrapper) {
        return accountBalanceCalcDao.selectList(model, wrapper);
    }

    @Override
    public List<AccountBalanceCalcModel> selectPage(Page pagination, AccountBalanceCalcModel model, Wrapper<AccountBalanceCalcModel> wrapper) {
        return accountBalanceCalcDao.selectPage(pagination, model, wrapper);
    }

}

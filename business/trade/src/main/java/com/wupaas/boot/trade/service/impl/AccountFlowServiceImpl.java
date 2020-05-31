package com.wupaas.boot.trade.service.impl;

import com.wupaas.boot.bussiness.base.service.BaseServiceCacheImpl;
import com.wupaas.boot.trade.repository.AccountFlowRepository;
import com.wupaas.boot.trade.model.AccountFlowModel;
import com.wupaas.boot.trade.model.AccountInfoModel;
import com.wupaas.boot.trade.service.IAccountFlowService;
import com.wupaas.boot.trade.service.IAccountInfoService;
import cn.afterturn.easypoi.handler.inter.IExcelExportServer;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Service
 * 账户流水号
 *
 * @author
 * @Date
 */
@Service
public class AccountFlowServiceImpl extends BaseServiceCacheImpl<AccountFlowRepository, AccountFlowModel> implements IAccountFlowService, IExcelExportServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountFlowServiceImpl.class);

    @Autowired
    private AccountFlowRepository accountFlowRepository;
    @Autowired
    private IAccountInfoService   accountInfoService;


    @Override
    public AccountFlowModel selectOne(AccountFlowModel entity) {
        return accountFlowRepository.selectOne(new QueryWrapper<>(entity));
    }

    @Override
    public List<AccountFlowModel> selectList(AccountFlowModel model) {
        return accountFlowRepository.selectList(model, new QueryWrapper<>());
    }

    @Override
    public List<AccountFlowModel> selectList(AccountFlowModel model, Wrapper<AccountFlowModel> wrapper) {
        return accountFlowRepository.selectList(model, wrapper);
    }

    @Override
    public List<AccountFlowModel> selectPage(Page pagination, AccountFlowModel model, Wrapper<AccountFlowModel> wrapper) {
        return accountFlowRepository.selectPage(pagination, model, wrapper);
    }

    @Override
    public void insertFlow(String accountNo, String tradeNo, int subject, int money, int fee, int realMoney, String memo) {
        this.save(getAccountFlowModel(accountNo, tradeNo, subject, money, fee, realMoney, memo, null, null, null, null));
    }

    private AccountFlowModel getAccountFlowModel(String accountNo, String tradeNo, int subject, int money, int fee, int realMoney, String memo, String settlement, String type, Date thirdTradeTime, Integer status) {
        AccountFlowModel entity = new AccountFlowModel();
        entity.setAccountNo(accountNo);
        entity.setTradeNo(tradeNo);
        entity.setSubject(subject + "");
        entity.setTradeTime(new Date());
        entity.setCurrency(1);
        entity.setMoney(money);
        entity.setFee(fee);
        entity.setRealMoney(realMoney);
        entity.setMemo(memo);
        entity.setCompleteTime(new Date());
        entity.setBalance(this.accountInfoService.selectOne(new AccountInfoModel(accountNo)).getBalance());

        entity.setTradeInfo(settlement);
        entity.setType(type);

        entity.setThirdTradeTime(thirdTradeTime);
        entity.setStatus(status);
        return entity;
    }

    @Override
    public void insertFlow(String accountNo, String tradeNo, int subject, int money, int fee, int realMoney, String memo, String settlement, String type) {
        this.save(getAccountFlowModel(accountNo, tradeNo, subject, money, fee, realMoney, memo, settlement, type, null, null));
    }

    @Override
    public void insertFlow(String accountNo, String tradeNo, int subject, int money, int fee, int realMoney, String memo, Date thirdTradeTime, String type, Integer status) {
        this.save(getAccountFlowModel(accountNo, tradeNo, subject, money, fee, realMoney, memo, null, type, thirdTradeTime, status));
    }

    @Override
    public int getTradeListMoney(String tradeNo, int subject) {
        return accountFlowRepository.getTradeListMoney(tradeNo, subject);
    }

    @Override
    public void updateOuterTradeNo(String sourceId, String[] tradeNoArr) {
        accountFlowRepository.updateOuterTradeNo(sourceId, tradeNoArr);
    }

    @Override
    public void updateStatus(List<AccountFlowModel> list, int status) {
        accountFlowRepository.updateStatus(list, status);
    }

    @Override
    public List<Object> selectListForExcelExport(Object obj, int pageNum) {
        QueryWrapper<AccountFlowModel> wrapper = (QueryWrapper<AccountFlowModel>) obj;
        Page<AccountFlowModel>         page    = new Page<AccountFlowModel>(pageNum, 100);
        return (List) selectPage(page, (AccountFlowModel) wrapper.getEntity(), wrapper);
    }
}

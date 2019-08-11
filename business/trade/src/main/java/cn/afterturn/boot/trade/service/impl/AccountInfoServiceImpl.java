package cn.afterturn.boot.trade.service.impl;

import cn.afterturn.boot.bussiness.base.service.BaseServiceCacheImpl;
import cn.afterturn.boot.core.util.DateUtil;
import cn.afterturn.boot.core.util.ToolUtil;
import cn.afterturn.boot.trade.common.exception.BizException;
import cn.afterturn.boot.trade.common.util.SerialNumberUtil;
import cn.afterturn.boot.trade.repository.AccountInfoRepository;
import cn.afterturn.boot.trade.model.AccountFlowModel;
import cn.afterturn.boot.trade.model.AccountInfoModel;
import cn.afterturn.boot.trade.model.PaymentModel;
import cn.afterturn.boot.trade.model.enums.AccountTypeEnum;
import cn.afterturn.boot.trade.model.enums.InternalAccountEnum;
import cn.afterturn.boot.trade.service.IAccountFlowService;
import cn.afterturn.boot.trade.service.IAccountInfoService;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Service
 * 账户基本信息
 *
 * @author
 * @Date
 */
@Service
public class AccountInfoServiceImpl extends BaseServiceCacheImpl<AccountInfoRepository, AccountInfoModel> implements IAccountInfoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountInfoServiceImpl.class);

    @Autowired
    private AccountInfoRepository accountInfoRepository;
    @Autowired
    private IAccountFlowService   accountFlowService;

    @Override
    public AccountInfoModel selectOne(AccountInfoModel model) {
        return accountInfoRepository.selectOne(new QueryWrapper<>(model));
    }

    @Override
    public List<AccountInfoModel> selectList(AccountInfoModel model) {
        return accountInfoRepository.selectList(model, new QueryWrapper<>());
    }

    @Override
    public List<AccountInfoModel> selectList(AccountInfoModel model, Wrapper<AccountInfoModel> wrapper) {
        return accountInfoRepository.selectList(model, wrapper);
    }

    @Override
    public List<AccountInfoModel> selectPage(Page pagination, AccountInfoModel model, Wrapper<AccountInfoModel> wrapper) {
        return accountInfoRepository.selectPage(pagination, model, wrapper);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public AccountInfoModel addAccount(String appid, String name, String userName, String userPhone) {
        AccountInfoModel model = new AccountInfoModel();
        model.setName(name);
        model.setAppid(appid);
        model.setAccountNo(SerialNumberUtil.getAccountSerialNo());
        model.setUserName(userName);
        model.setUserPhone(userPhone);
        model.setBalance(0L);
        model.setFrozenBalance(0L);
        model.setCrtTime(new Date());
        model.setCrtUserId("1");
        model.setCurrency(1);
        model.setIsBalanceNegative(1);
        model.setIsCash(1);
        model.setType(AccountTypeEnum.USER.getCode());
        model.setIsRecharge(1);
        model.setOpenTime(new Date());
        String balCheck = ToolUtil.getBalCheck(model.getBalance(), model.getAccountNo());
        if (this.save(model) && accountInfoRepository.calBalanceCheck(model.getAccountNo(), balCheck) == 1) {
            return model;
        }
        throw new RuntimeException("插入数据失败");
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Long recharge(String tradeNo, String accountNo, int money) {
        int result = this.accountInfoRepository.recharge(accountNo, money);
        if (result != 1) {
            throw new RuntimeException("充值异常,账户:" + accountNo + " 金额:" + money);
        }
        // 插入流水
        accountFlowService.insertFlow(accountNo, tradeNo, 1001, money, 0, money, accountNo + "充值," + money / 100 + "分");
        return this.accountInfoRepository.selectOne(new QueryWrapper<AccountInfoModel>().eq("account_no", accountNo)).getBalance();
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Long transferToFrozen(String tradeNo, String fromAccount, String toAccountNo, int money, int fromSubject, int toSubject, String type, String tradeTime) {
        if (this.accountInfoRepository.selectOne(new QueryWrapper<AccountInfoModel>().eq("account_no", fromAccount)).getBalance() < money) {
            throw new BizException(-2, "余额不足");
        }
        // 从出账户扣钱
        int result = this.accountInfoRepository.forward(fromAccount, money);
        if (result != 1) {
            throw new RuntimeException("转账异常,账户:" + fromAccount + " 金额:" + money);
        }
        // 插入转出流水
        accountFlowService.insertFlow(fromAccount, tradeNo, fromSubject, money, 0, money, fromAccount + "转出," + money + "分", DateUtil.parse(tradeTime, "yyyyMMddHHmmss"), type, 6);
        // 转入充值账户
        result = this.accountInfoRepository.rechargeToFrozen(toAccountNo, money);
        if (result != 1) {
            throw new RuntimeException("转账异常,账户:" + toAccountNo + " 金额:" + money);
        }
        // 插入转出流水
        accountFlowService.insertFlow(toAccountNo, tradeNo, toSubject, money, 0, money, toAccountNo + "转入," + money + "分", DateUtil.parse(tradeTime, "yyyyMMddHHmmss"), type, 6);
        return this.selectOne(new AccountInfoModel(toAccountNo)).getBalance();
    }


    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Long transfer(String tradeNo, String fromAccount, String toAccountNo, int money,
                         int fromSubject, int toSubject, String type) {
        if (this.selectOne(new AccountInfoModel(fromAccount)).getBalance() < money) {
            throw new BizException(-2, "余额不足");
        }
        // 从出账户扣钱
        int result = this.accountInfoRepository.forward(fromAccount, money);
        if (result != 1) {
            throw new RuntimeException("转账异常,账户:" + fromAccount + " 金额:" + money);
        }
        // 插入转出流水
        accountFlowService.insertFlow(fromAccount, tradeNo, fromSubject, money, 0, money, fromAccount + "转出," + money + "分", null, type);
        // 转入充值账户
        result = this.accountInfoRepository.recharge(toAccountNo, money);
        if (result != 1) {
            throw new RuntimeException("转账异常,账户:" + toAccountNo + " 金额:" + money);
        }
        // 插入转出流水
        accountFlowService.insertFlow(toAccountNo, tradeNo, toSubject, money, 0, money, toAccountNo + "转入," + money + "分", null, type);
        return this.selectOne(new AccountInfoModel(toAccountNo)).getBalance();
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Long forward(String appId, String tradeNo, String accountNo, PaymentModel settlement, int money) {
        AccountInfoModel target = this.selectOne(new AccountInfoModel(accountNo));
        if (target.getBalance() < money) {
            throw new BizException(-2, "余额不足");
        }
        // 从出账户扣钱
        int result = this.accountInfoRepository.forward(accountNo, money);
        if (result != 1) {
            throw new RuntimeException("提现异常,账户:" + accountNo + " 金额:" + money);
        }
        if (StringUtils.isEmpty(settlement.getToCardMobile())) {
            settlement.setToCardMobile(target.getName());
        }
        // 插入转出流水
        accountFlowService.insertFlow(accountNo, tradeNo, 3002, money, 0, money, accountNo + "转出," + money + "分", JSON.toJSONString(settlement), null);
        //转入到内部户
        this.accountInfoRepository.recharge(InternalAccountEnum.FORWARD, money);
        // 插入转出流水
        accountFlowService.insertFlow(InternalAccountEnum.FORWARD, tradeNo, 6001, money, 0, money, accountNo + "提现转入," + money + "分", JSON.toJSONString(settlement), null);
        return this.selectOne(new AccountInfoModel(accountNo)).getBalance();
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Long forward(String appId, String tradeNo, String[] accountNos, PaymentModel settlement, int money, int fee) {
        int forwardMoney   = money;
        int forwardedMoney = 0;
        //手续费是否已经扣除
        boolean feed = fee == 0;
        for (int i = 0; i < accountNos.length; i++) {
            if (forwardedMoney == money) {
                break;
            }
            String           accountNo = accountNos[i];
            AccountInfoModel target    = this.selectOne(new AccountInfoModel(accountNo));
            if (target.getBalance() < fee && fee > 0) {
                // 余额小于手续费,默认余额不足跳过
                continue;
            }
            //先转出手续费
            if (!feed) {
                // 从出账户扣钱
                int result = this.accountInfoRepository.forward(accountNo, fee);
                if (result != 1) {
                    throw new RuntimeException("提现异常,余额不足,账户:" + accountNo + " 金额:" + money);
                }
                if (StringUtils.isEmpty(settlement.getToCardMobile())) {
                    settlement.setToCardMobile(target.getName());
                }
                // 插入转出流水
                accountFlowService.insertFlow(accountNo, tradeNo + "_" + accountNo + "_FEE", 3002, fee, 0, fee, accountNo + "转出手续费," + fee + "分");
                //转入到手续费内部户
                accountInfoRepository.recharge(InternalAccountEnum.FORWARD_FEE, fee);
                // 插入转出流水
                accountFlowService.insertFlow(InternalAccountEnum.FORWARD_FEE, tradeNo + "_" + accountNo + "_FEE", 6001, fee, 0, fee, accountNo + "提现手续费转入," + fee + "分");
                feed = true;
            }

            if (target.getBalance() > money + fee - forwardedMoney) {
                forwardMoney = money - forwardedMoney;
            } else if (i < accountNos.length - 1) {
                forwardMoney = target.getBalance().intValue() - fee;
            } else {
                forwardMoney = money - forwardedMoney;
            }
            // 从出账户扣钱
            int result = this.accountInfoRepository.forward(accountNo, forwardMoney);
            if (result != 1) {
                throw new BizException(-2, "余额不足");
            }
            // 插入转出流水
            accountFlowService.insertFlow(accountNo, tradeNo + "_" + accountNo, 3002, forwardMoney, 0, forwardMoney, accountNo + "转出," + forwardMoney + "分");
            //转入到内部户
            this.accountInfoRepository.recharge(InternalAccountEnum.FORWARD, forwardMoney);
            // 插入转出流水
            accountFlowService.insertFlow(InternalAccountEnum.FORWARD, tradeNo + "_" + accountNo, 6001, forwardMoney, 0, forwardMoney, accountNo + "提现转入," + money + "分");
            forwardedMoney += forwardMoney;
            if (forwardedMoney > money) {
                throw new RuntimeException("提现异常,提现超出限制,账户:" + accountNo + " 金额:" + money + " 实提金额:" + forwardedMoney);
            }
        }
        if (forwardedMoney < money) {
            throw new BizException(-2, "余额不足");
        }
        return 0L;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Long refund(String[] tradeNos, String[] accountNos, int type, int fromSubject, int toSubject) {
        AccountInfoModel accountInfoModel = new AccountInfoModel();
        // 单账户提现,不带手续费
        if (type == 2 && tradeNos.length == 2) {
            //检查 源订单是否存在
            Wrapper<AccountFlowModel> wrapper     = new QueryWrapper<AccountFlowModel>().eq("trade_no", tradeNos[0]);
            AccountFlowModel          sourceModel = accountFlowService.selectList(new AccountFlowModel(), wrapper).get(0);
            if (sourceModel == null) {
                throw new BizException(-102, "源订单不存在存在");
            }
            return transfer(tradeNos[0] + "_6002", InternalAccountEnum.FORWARD, accountNos[0], sourceModel.getMoney(), fromSubject, toSubject, null);
        } else {
            for (int i = 0; i < tradeNos.length; i++) {
                Wrapper<AccountFlowModel> wrapper    = new QueryWrapper<AccountFlowModel>().eq("trade_no", tradeNos[i]);
                List<AccountFlowModel>    sourceList = accountFlowService.selectList(new AccountFlowModel(), wrapper);
                if (sourceList != null && sourceList.size() > 0) {
                    if (tradeNos[i].endsWith("_FEE")) {
                        transfer(tradeNos[i] + "_6002", InternalAccountEnum.FORWARD_FEE, accountNos[i / 2], sourceList.get(0).getMoney(), fromSubject, toSubject, null);
                    } else {
                        transfer(tradeNos[i] + "_6002", InternalAccountEnum.FORWARD, accountNos[i / 2], sourceList.get(0).getMoney(), fromSubject, toSubject, null);
                    }
                }
            }
        }
        return 0L;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void batchTransfer(String appId, String sourceId, List<Map<String, String>> list, int fromSubject, int toSubject) {
        String   tradeNo, fromAccount, toAccountNo, money;
        String[] tradeNoArr = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            fromAccount = list.get(i).get("fromAccount");
            tradeNo = list.get(i).get("tradeNo");
            toAccountNo = list.get(i).get("toAccountNo");
            money = list.get(i).get("money");
            AccountInfoModel model = new AccountInfoModel();
            model.setAppid(appId);
            model.setAccountNo(fromAccount);
            AccountInfoModel account = this.selectOne(model);
            if (account == null) {
                throw new BizException(-102, "付款账户不存在存在:" + fromAccount);
            }
            model.setAccountNo(toAccountNo);
            account = this.selectOne(model);
            if (account == null) {
                throw new BizException(-102, "收款账户不存在存在:" + toAccountNo);
            }
            transfer(appId + "_" + tradeNo, fromAccount, toAccountNo, Integer.parseInt(money), fromSubject, toSubject, null);
            tradeNoArr[i] = appId + "_" + tradeNo;
        }
        //批量更新下关联订单号
        accountFlowService.updateOuterTradeNo(sourceId, tradeNoArr);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public synchronized int sysFrozenToBal(String tradeNo, String appid, String accountNo, String startTime, String endTime) {
        // 查询所有 当前时间段的冻结订单
        List<AccountFlowModel> list = accountFlowService.selectList(new AccountFlowModel(), new QueryWrapper<AccountFlowModel>().eq("account_no", accountNo).eq("subject", 3003)
                .ge("third_trade_time", DateUtil.parse(startTime, "yyyyMMddHHmmss")).lt("third_trade_time", DateUtil.parse(endTime, "yyyyMMddHHmmss")));
        if (list.size() == 0) {
            throw new BizException(-102, "该时间段不存在订单,请换个时间段");
        }
        int money = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getStatus() != 6) {
                throw new BizException(-102, "该时间段存在已经入账的订单,请换个时间段");
            }
            money += list.get(i).getRealMoney();
        }

        accountFlowService.updateStatus(list, 7);

        //冻结账户提现
        int result = this.accountInfoRepository.forwardFromFrozen(accountNo, money);
        if (result != 1) {
            throw new BizException(-2, "余额不足");
        }
        // 插入转出流水
        accountFlowService.insertFlow(accountNo, tradeNo, 3004, money, 0, money, accountNo + "冻结入账转出," + money + "分");
        //转入到内部户
        this.accountInfoRepository.recharge(accountNo, money);
        // 插入转出流水
        accountFlowService.insertFlow(accountNo, tradeNo, 3001, money, 0, money, accountNo + "冻结金额入账 ----转入," + money + "分");
        return money;
    }

}

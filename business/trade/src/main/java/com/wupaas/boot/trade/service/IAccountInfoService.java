package com.wupaas.boot.trade.service;

import com.wupaas.boot.core.business.base.service.IBaseService;
import com.wupaas.boot.trade.model.AccountInfoModel;
import com.wupaas.boot.trade.model.PaymentModel;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.Map;


/**
 * Service
 * 账户基本信息
 *
 * @author
 * @Date
 */
public interface IAccountInfoService extends IBaseService<AccountInfoModel> {

    /**
     * <p>
     * 根据 model 条件，查询一条记录
     * </p>
     *
     * @param model 实体对象 非空
     * @return AccountInfoModel
     */
    AccountInfoModel selectOne(AccountInfoModel model);

    /**
     * <p>
     * 根据 model 条件，查询全部记录
     * </p>
     *
     * @param model 实体对象封装操作类（可以为 null）
     * @return List<AccountInfoModel>
     */
    List<AccountInfoModel> selectList(AccountInfoModel model);

    /**
     * <p>
     * 根据 model 条件，查询全部记录
     * </p>
     *
     * @param model   实体对象封装操作类（可以为 null）
     * @param wrapper SQL包装
     * @return List<AccountInfoModel>
     */
    List<AccountInfoModel> selectList(AccountInfoModel model, Wrapper<AccountInfoModel> wrapper);


    /**
     * <p>
     * 根据 model 条件，查询全部记录（并翻页）
     * </p>
     *
     * @param pagination 分页查询条件
     * @param model      实体对象封装操作可以为 null）
     * @param wrapper    SQL包装
     * @return List<AccountInfoModel>
     */
    List<AccountInfoModel> selectPage(Page pagination, AccountInfoModel model, Wrapper<AccountInfoModel> wrapper);

    /**
     * 新开账户
     *
     * @param appid
     * @param name
     * @param userName
     * @param userPhone
     * @return
     */
    AccountInfoModel addAccount(String appid, String name, String userName, String userPhone);


    /**
     * 充值
     * 从APPId的账户中扣钱,然后转入用户账户
     *
     * @param companyId
     * @param money
     * @return
     */
    Long recharge(String tradeNo, String companyId, int money);

    /**
     * 转账
     *
     * @param fromAccount
     * @param toAccountNo
     * @param money
     * @return
     */
    Long transfer(String tradeNo, String fromAccount, String toAccountNo, int money, int fromSubject, int toSubject, String type);

    /**
     * 转账到冻结余额
     * @param tradeNo
     * @param fromAccount
     * @param toAccountNo
     * @param money
     * @param fromSubject
     * @param toSubject
     * @param type
     * @param tradeTime
     * @return
     */
    Long transferToFrozen(String tradeNo, String fromAccount, String toAccountNo, int money, int fromSubject, int toSubject, String type, String tradeTime);

    /**
     * 提现
     *
     * @param appid
     * @param accountNo
     * @param settlement
     * @param money
     * @return
     */
    Long forward(String appid, String tradeNo, String accountNo, PaymentModel settlement, int money);

    /**
     * 多账户提现
     *
     * @param appid
     * @param tradeNo
     * @param accountNos
     * @param settlement
     * @param money
     * @param fee
     * @return
     */
    Long forward(String appid, String tradeNo, String[] accountNos, PaymentModel settlement, int money, int fee);

    /**
     * 多账户批量退款
     *
     * @param tradeNo
     * @param type
     * @param fromSubject
     * @param toSubject
     * @return
     */
    Long refund(String[] tradeNo, String[] accountNos, int type, int fromSubject, int toSubject);

    /**
     * 批量转账接口
     *
     * @param appid    APPI
     * @param sourceId 统一订单号
     * @param list     子订单
     * @param fromSubject   转出科目
     * @param toSubject     转入科目
     */
    void batchTransfer(String appid, String sourceId, List<Map<String, String>> list, int fromSubject, int toSubject);

    /**
     * 不可提现余额,入账到可提现余额
     *  synchronized 线程锁,单线程,防止并发 -- 一天最多调用个几次,不用考虑效率
     * @param tradeNo
     * @param appid
     * @param tradeNo
     * @param accountNo
     * @param startTime
     * @param endTime
     * @return
     */
    int sysFrozenToBal(String tradeNo, String appid, String accountNo, String startTime, String endTime);
}

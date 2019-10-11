package cn.afterturn.boot.trade.service;

import cn.afterturn.boot.bussiness.base.service.IBaseService;
import cn.afterturn.boot.trade.model.AccountFlowModel;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Date;
import java.util.List;


/**
 * Service
 * 账户流水号
 *
 * @author
 * @Date
 */
public interface IAccountFlowService extends IBaseService<AccountFlowModel> {

    /**
     * <p>
     * 根据 model 条件，查询一条记录
     * </p>
     *
     * @param model 实体对象 非空
     * @return AccountFlowModel
     */
    AccountFlowModel selectOne(AccountFlowModel model);

    /**
     * <p>
     * 根据 model 条件，查询全部记录
     * </p>
     *
     * @param model 实体对象封装操作类（可以为 null）
     * @return List<AccountFlowModel>
     */
    List<AccountFlowModel> selectList(AccountFlowModel model);

    /**
     * <p>
     * 根据 model 条件，查询全部记录
     * </p>
     *
     * @param model   实体对象封装操作类（可以为 null）
     * @param wrapper SQL包装
     * @return List<AccountFlowModel>
     */
    List<AccountFlowModel> selectList(AccountFlowModel model, Wrapper<AccountFlowModel> wrapper);


    /**
     * <p>
     * 根据 model 条件，查询全部记录（并翻页）
     * </p>
     *
     * @param pagination 分页查询条件
     * @param model      实体对象封装操作可以为 null）
     * @param wrapper    SQL包装
     * @return List<AccountFlowModel>
     */
    List<AccountFlowModel> selectPage(Page pagination, AccountFlowModel model, Wrapper<AccountFlowModel> wrapper);

    /**
     * 插入流水
     *
     * @param tradeNo
     * @param subject
     * @param money
     * @param fee
     * @param realMoney
     */
    void insertFlow(String accountNo, String tradeNo, int subject, int money, int fee, int realMoney, String memo);

    /**
     * 插入流水
     *
     * @param tradeNo
     * @param subject
     * @param money
     * @param fee
     * @param realMoney
     */
    void insertFlow(String accountNo, String tradeNo, int subject, int money, int fee, int realMoney, String memo, String settlement, String type);

    /**
     * 插入流水
     *
     * @param accountNo
     * @param tradeNo
     * @param subject
     * @param money
     * @param fee
     * @param realMoney
     * @param memo
     * @param thirdTradeTime
     * @param type
     * @param status
     */
    void insertFlow(String accountNo, String tradeNo, int subject, int money, int fee, int realMoney, String memo, Date thirdTradeTime, String type, Integer status);

    /**
     * 获取订单的交易金额
     *
     * @param tradeNo
     * @param subject
     * @return
     */
    int getTradeListMoney(String tradeNo, int subject);

    /**
     * 更新下关联订单号
     *
     * @param outerTradeNo
     * @param tradeNoArr
     */
    void updateOuterTradeNo(String outerTradeNo, String[] tradeNoArr);

    /**
     * 根据订单号,批量更新订单
     *
     * @param list
     * @param status
     */
    void updateStatus(List<AccountFlowModel> list, int status);
}

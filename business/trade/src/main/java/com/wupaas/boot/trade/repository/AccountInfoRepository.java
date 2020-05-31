package com.wupaas.boot.trade.repository;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wupaas.boot.trade.model.AccountInfoModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * AccountInfoDao
 * 账户基本信息
 *
 * @author
 * @Date
 */
@Repository
public interface AccountInfoRepository extends BaseMapper<AccountInfoModel> {

    /**
     * 查询列表
     *
     * @param model
     * @return
     */
    List<AccountInfoModel> selectList(@Param("e") AccountInfoModel model, @Param("w") Wrapper<AccountInfoModel> wrapper);

    /**
     * 分页查询信息
     *
     * @param pagination
     * @param model
     * @param wrapper
     * @return
     */
    List<AccountInfoModel> selectPage(@Param("p") Page pagination, @Param("e") AccountInfoModel model, @Param("w") Wrapper<AccountInfoModel> wrapper);

    /**
     * 初始化余额校验值
     *
     * @param accountNo
     */
    int calBalanceCheck(@Param("accountNo") String accountNo, @Param("balCheck") String balCheck);

    /**
     * 充值
     *
     * @param accountNo
     * @param money
     */
    int recharge(@Param("accountNo") String accountNo, @Param("money") int money);

    /**
     * 充值
     *
     * @param accountNo
     * @param money
     */
    int rechargeToFrozen(@Param("accountNo") String accountNo, @Param("money") int money);

    /**
     * 提现
     *
     * @param accountNo
     * @param money
     * @return
     */
    int forward(@Param("accountNo") String accountNo, @Param("money") int money);

    /**
     * 冻结户入账
     *
     * @param accountNo
     * @param money
     * @return
     */
    int forwardFromFrozen(@Param("accountNo") String accountNo, @Param("money") int money);

    /**
     * 锁行获取对象
     *
     * @param accountInfoModel
     * @return
     */
    AccountInfoModel selectForUpdate(@Param("t") AccountInfoModel accountInfoModel);
}

package cn.afterturn.boot.trade.dao;

import cn.afterturn.boot.trade.model.AccountFlowModel;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * AccountFlowDao
 * 账户流水号
 *
 * @author
 * @Date
 */
@Repository
public interface AccountFlowDao extends BaseMapper<AccountFlowModel> {

    /**
     * 查询列表
     *
     * @param model
     * @return
     */
    List<AccountFlowModel> selectList(@Param("e") AccountFlowModel model, @Param("ew") Wrapper<AccountFlowModel> wrapper);

    /**
     * 分页查询信息
     *
     * @param pagination
     * @param model
     * @param wrapper
     * @return
     */
    List<AccountFlowModel> selectPage(@Param("p") Page pagination, @Param("e") AccountFlowModel model, @Param("ew") Wrapper<AccountFlowModel> wrapper);

    /**
     * 获取订单交易金额
     *
     * @param tradeNo
     * @param subject
     * @return
     */
    int getTradeListMoney(@Param("tradeNo") String tradeNo, @Param("subject") int subject);

    void updateOuterTradeNo(@Param("outerTradeNo") String outerTradeNo, @Param("tradeNoArr") String[] tradeNoArr);

    void updateStatus(@Param("list") List<AccountFlowModel> list, @Param("status") int status);
}

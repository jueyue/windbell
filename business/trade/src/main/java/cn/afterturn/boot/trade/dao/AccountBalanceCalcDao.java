
package cn.afterturn.boot.trade.dao;

import cn.afterturn.boot.trade.model.AccountBalanceCalcModel;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * AccountBalanceCalcDao
 * 总分平衡
 *
 * @author JueYue
 * @Date 2018-09-12 19:47:54
 */
@Repository
public interface AccountBalanceCalcDao extends BaseMapper<AccountBalanceCalcModel> {

    /**
     * 查询列表
     *
     * @param model
     * @return
     */
    List<AccountBalanceCalcModel> selectList(@Param("e") AccountBalanceCalcModel model, @Param("ew") Wrapper<AccountBalanceCalcModel> wrapper);

    /**
     * 分页查询信息
     *
     * @param pagination
     * @param model
     * @param wrapper
     * @return
     */
    List<AccountBalanceCalcModel> selectPage(@Param("p") Page pagination, @Param("e") AccountBalanceCalcModel model, @Param("ew") Wrapper<AccountBalanceCalcModel> wrapper);

}

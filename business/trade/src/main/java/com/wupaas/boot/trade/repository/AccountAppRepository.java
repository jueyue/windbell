package com.wupaas.boot.trade.repository;

import com.wupaas.boot.trade.model.AccountAppModel;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * AccountAppDao
 * 应用
 *
 * @author
 * @Date
 */
@Repository
public interface AccountAppRepository extends BaseMapper<AccountAppModel> {

    /**
     * 查询列表
     *
     * @param model
     * @return
     */
    List<AccountAppModel> selectList(@Param("e") AccountAppModel model, @Param("w") Wrapper<AccountAppModel> wrapper);

    /**
     * 分页查询信息
     *
     * @param pagination
     * @param model
     * @param wrapper
     * @return
     */
    List<AccountAppModel> selectPage(@Param("p") Page pagination, @Param("e") AccountAppModel model, @Param("w") Wrapper<AccountAppModel> wrapper);

}

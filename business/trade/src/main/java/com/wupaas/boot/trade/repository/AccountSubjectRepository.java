package com.wupaas.boot.trade.repository;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wupaas.boot.trade.model.AccountSubjectModel;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * AccountSubjectDao
 * 账户科目表
 *
 * @author
 * @Date
 */
@Repository
public interface AccountSubjectRepository extends BaseMapper<AccountSubjectModel> {

    /**
     * 查询列表
     *
     * @param model
     * @return
     */
    List<AccountSubjectModel> selectList(@Param("e") AccountSubjectModel model, @Param("w") Wrapper<AccountSubjectModel> wrapper);

    /**
     * 分页查询信息
     *
     * @param pagination
     * @param model
     * @param wrapper
     * @return
     */
    List<AccountSubjectModel> selectPage(@Param("p") Page pagination, @Param("e") AccountSubjectModel model, @Param("w") Wrapper<AccountSubjectModel> wrapper);

}

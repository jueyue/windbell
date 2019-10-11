package cn.afterturn.boot.bussiness.base.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 通用基础Service
 *
 * @author by jueyue on 18-6-28.
 */
public interface IBaseService<T> extends IService<T> {

    /**
     * <p>
     * 根据 model 条件，查询一条记录
     * </p>
     *
     * @param model 实体对象 非空
     * @return T
     */
    T getOne(T model);

    /**
     * <p>
     * 根据 model 条件，查询全部记录
     * </p>
     *
     * @param model 实体对象封装操作类（可以为 null）
     * @return List<T>
     */
    List<T> list(T model);

}

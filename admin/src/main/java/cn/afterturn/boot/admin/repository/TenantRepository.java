package cn.afterturn.boot.admin.repository;

import cn.afterturn.boot.admin.model.TenantModel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * Tenant持久化
 *
 * @author
 * @Date 2018-09-03 23:21:00
 */
@Repository
public interface TenantRepository extends BaseMapper<TenantModel> {

}

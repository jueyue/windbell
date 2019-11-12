package cn.afterturn.boot.facade.admin;

import cn.afterturn.boot.bussiness.response.Response;
import cn.afterturn.boot.facade.admin.entity.TenantEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 接口
 *
 * @author
 * @Date 2018-09-03 23:26:08
 */
@FeignClient(value = "tenantFacade", contextId = "lemur-admin", path = "/tenant")
public interface ITenantFacade {

    /**
     * 查询客户信息
     *
     * @param tenantId
     * @return
     */
    @ApiOperation(value = "查询用户产品")
    @GetMapping(value = "/getTenantByTenantId/{tenantId}")
    Response<TenantEntity> getTenantByTenantId(@PathVariable("tenantId") String tenantId);
}

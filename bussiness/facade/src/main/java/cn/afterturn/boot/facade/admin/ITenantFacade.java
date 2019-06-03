package cn.afterturn.boot.facade.admin;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * 接口
 *
 * @author
 * @Date 2018-09-03 23:26:08
 */
@FeignClient(value = "tenantFacade")
public interface ITenantFacade {

}

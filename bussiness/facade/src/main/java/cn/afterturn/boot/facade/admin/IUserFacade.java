package cn.afterturn.boot.facade.admin;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * 用户接口
 *
 * @author by jueyue on 18-9-3.
 */
@FeignClient(value = "userFacade")
public interface IUserFacade {
}

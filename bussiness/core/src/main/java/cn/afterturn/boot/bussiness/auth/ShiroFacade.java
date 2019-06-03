package cn.afterturn.boot.bussiness.auth;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author by jueyue on 19-1-29.
 */
@FeignClient(value = "shiroFacade")
public class ShiroFacade {
}

package cn.afterturn.boot.facade.paas.risk;

import cn.afterturn.boot.bussiness.response.Response;
import cn.afterturn.boot.facade.paas.risk.entity.AntiFraudEntity;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 反欺诈相关风控信息
 *
 * @author jueyue on 19-11-16.
 */
@FeignClient(value = "antiFraudFacade", contextId = "lemur-paas", path = "/risk")
public interface IAntiFraudFacade {

    /**
     * 反欺诈三要数认证
     *
     * @param phone
     * @param name
     * @param idcard
     * @param tenantId
     * @return
     */
    @ApiOperation("反欺诈")
    @PostMapping("/antiFraud")
    public Response<AntiFraudEntity> antiFraud(@ApiParam("手机号") @RequestParam(name = "phone") String phone,
                                               @ApiParam("姓名") @RequestParam(name = "name") String name,
                                               @ApiParam("身份证") @RequestParam(name = "idcard") String idcard,
                                               @ApiParam("客户") @RequestParam(name = "tenantId") String tenantId);
}
